package org.dedee.kompack.mpack.unpack

class IntTypeUnpacker : TypeUnpacker<Int> {

    override fun unpack(source: Source): Int? {
        return unpackInt(source)
    }


    private fun unpackInt(source: Source): Int? {
        val b = source.pullByte()

        // nil:
        // +------+
        // | 0xc0 |
        // +------+
        if (b == 0xc0) {
            return null
        }

        val ret: Int
        if (b and (1 shl 7) == 0) {
            // positive fixnum stores 7-bit positive integer
            // +--------+
            // |0XXXXXXX|
            // +--------+
            ret = b and 0x7f
        } else {
            if (b and 0xe0 == 0xe0) {
                // negative fixnum stores 5-bit negative integer
                // +--------+
                // |111YYYYY|
                // +--------+
                ret = (b and 0x1f) - 0x20
            } else {
                when (b) {
                    0xd0 -> {
                        // int 8 stores a 8-bit signed integer
                        // +------+--------+
                        // | 0xd0 |ZZZZZZZZ|
                        // +------+--------+
                        ret = source.pullByte().toByte().toInt()

                        // end::[]
                    }

                    0xd1 -> {
                        // int 16 stores a 16-bit big-endian signed integer
                        // +------+--------+--------+
                        // | 0xd1 |ZZZZZZZZ|ZZZZZZZZ|
                        // -------+--------+--------+
                        ret = source.pullInt16()
                    }

                    0xd2 -> {
                        // int 32 stores a 32-bit big-endian signed integer
                        // +------+--------+--------+--------+--------+
                        // | 0xd2 |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
                        // +------+--------+--------+--------+--------+
                        ret = source.pullInt32()
                    }

                    0xcc -> {
                        // unsigned 8-bit XXXXXXXX
                        // +------+--------+
                        // | 0xcc |XXXXXXXX|
                        // +------+--------+
                        ret = source.pullByte()
                    }

                    0xcd -> {
                        // uint 16 stores a 16-bit big-endian unsigned integer
                        // +------+--------+--------+
                        // | 0xcd |ZZZZZZZZ|ZZZZZZZZ|
                        // +------+--------+--------+
                        ret = source.pullInt16().and(0xffff)
                    }

                    else -> {
                        throw Exception()
                    }
                }
            }
        }
        return ret
    }


}


