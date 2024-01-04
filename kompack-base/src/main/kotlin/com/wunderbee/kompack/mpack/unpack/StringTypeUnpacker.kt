package com.wunderbee.kompack.mpack.unpack

class StringTypeUnpacker : TypeUnpacker<String> {

    override fun unpack(source: Source): String? {
        val b = source.pullByte()

        // nil:
        // +------+
        // | 0xc0 |
        // +------+
        if (b == 0xc0) {
            return null
        }

        // XXXXX is a 5-bit unsigned integer which represents N
        // YYYYYYYY is a 8-bit unsigned integer which represents N
        // ZZZZZZZZ_ZZZZZZZZ is a 16-bit big-endian unsigned integer which represents N
        // AAAAAAAA_AAAAAAAA_AAAAAAAA_AAAAAAAA is a 32-bit big-endian unsigned integer which represents N
        // N is the length of data
        val len: Int =
            if (b.and(0b10100000) == 0b10100000) {
                // fixstr stores a byte array whose length is upto 31 bytes:
                //+--------+========+
                //|101XXXXX|  data  |
                //+--------+========+
                b.and(0b00011111)
            } else {
                when (b) {
                    0xd9 -> {
                        //str 8 stores a byte array whose length is upto (2^8)-1 bytes:
                        //+--------+--------+========+
                        //|  0xd9  |YYYYYYYY|  data  |
                        //+--------+--------+========+
                        source.pullByte()
                    }

                    0xda -> {
                        //str 16 stores a byte array whose length is upto (2^16)-1 bytes:
                        //+--------+--------+--------+========+
                        //|  0xda  |ZZZZZZZZ|ZZZZZZZZ|  data  |
                        //+--------+--------+--------+========+
                        source.pullInt16()
                    }

                    0xdb -> {
                        //str 32 stores a byte array whose length is upto (2^32)-1 bytes:
                        //+--------+--------+--------+--------+--------+========+
                        //|  0xdb  |AAAAAAAA|AAAAAAAA|AAAAAAAA|AAAAAAAA|  data  |
                        //+--------+--------+--------+--------+--------+========+
                        source.pullInt32()
                    }

                    else -> {
                        throw Exception("Could not unpack, unknown type $b")
                    }
                }
            }

        val ba = source.pullBytes(len)
        return ba.decodeToString() // FIXME Check this
    }
}