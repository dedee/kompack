package com.wunderbee.kompack.mpack.unpack

class ArrayTypeUnpacker : TypeUnpacker<Array<Any>> {


    override fun unpack(source: Source): Array<Any>? {

        // fixarray stores an array whose length is upto 15 elements:
        //+--------+~~~~~~~~~~~~~~~~~+
        //|1001XXXX|    N objects    |
        //+--------+~~~~~~~~~~~~~~~~~+
        //
        //array 16 stores an array whose length is upto (2^16)-1 elements:
        //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //|  0xdc  |YYYYYYYY|YYYYYYYY|    N objects    |
        //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //
        //array 32 stores an array whose length is upto (2^32)-1 elements:
        //+--------+--------+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //|  0xdd  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|    N objects    |
        //+--------+--------+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //
        //where
        //* XXXX is a 4-bit unsigned integer which represents N
        //* YYYYYYYY_YYYYYYYY is a 16-bit big-endian unsigned integer which represents N
        //* ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ is a 32-bit big-endian unsigned integer which represents N
        //* N is the size of an array

        val b = source.pullByte()
        val len = if (b.and(0xf0) == 0x90) {
            b.and(0x0f)
        } else {
            when (b) {
                0xc0 -> {
                    // nil:
                    // +------+
                    // | 0xc0 |
                    // +------+
                    return null
                }

                0xdc -> {
                    source.pullInt16()
                }

                0xdd -> {
                    source.pullInt32()
                }

                else -> {
                    throw Exception("Could not unpack, unknown type $b")
                }
            }
        }

        val ret = mutableListOf<Any>()
        val unpacker = Unpacker(source)
        for (i in 1..len) {
            ret.add(unpacker.unpack()!!)
        }

        return ret.toTypedArray()
    }
}