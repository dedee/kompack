package org.dedee.kompack.mpack.unpack

class MapTypeUnpacker : TypeUnpacker<Map<Any, Any?>> {
    override fun unpack(source: Source): Map<Any, Any?>? {
        // fixmap stores a map whose length is upto 15 elements
        //+--------+~~~~~~~~~~~~~~~~~+
        //|1000XXXX|   N*2 objects   |
        //+--------+~~~~~~~~~~~~~~~~~+
        //
        //map 16 stores a map whose length is upto (2^16)-1 elements
        //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //|  0xde  |YYYYYYYY|YYYYYYYY|   N*2 objects   |
        //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //
        //map 32 stores a map whose length is upto (2^32)-1 elements
        //+--------+--------+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //|  0xdf  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|   N*2 objects   |
        //+--------+--------+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //
        //where
        //* XXXX is a 4-bit unsigned integer which represents N
        //* YYYYYYYY_YYYYYYYY is a 16-bit big-endian unsigned integer which represents N
        //* ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ is a 32-bit big-endian unsigned integer which represents N
        //* N is the size of a map
        //* odd elements in objects are keys of a map
        //* the next element of a key is its associated value

        val b = source.pullByte()

        val len = if (b.and(0xf0) == 0b1000_0000) {
            b.and(0x0f)
        } else {
            when (b) {
                0xc0 -> return null
                0xde -> source.pullInt16()
                0xdf -> source.pullInt32()
                else -> throw Exception()
            }
        }

        val ret = mutableMapOf<Any, Any?>()
        val unpacker = Unpacker(source)
        for (i in 1..len) {
            ret.put(unpacker.unpack(source)!!, unpacker.unpack(source))
        }

        return ret

    }
}