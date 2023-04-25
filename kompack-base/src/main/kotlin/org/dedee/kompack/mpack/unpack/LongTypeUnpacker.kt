package org.dedee.kompack.mpack.unpack

class LongTypeUnpacker : TypeUnpacker<Long> {

    private val intTypeUnpacker = IntTypeUnpacker()

    override fun unpack(source: Source): Long? {
        return unpackLong(source)
    }


    private fun unpackLong(source: Source): Long? {
        val b = source.pullByte()

        // nil:
        // +------+
        // | 0xc0 |
        // +------+
        if (b == 0xc0) {
            return null
        }

        val l: Long
        when (b) {

            // UNSIGNED INT HANDLED HERE!
            0xce -> {
                // uint 32 stores a 32-bit big-endian unsigned integer
                // +------+--------+--------+--------+--------+
                // | 0xce |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ
                // +------+--------+--------+--------+--------+
                l = source.pullInt32().toLong().and(0xffffffff)
            }

            0xd3 -> {
                // int 64 stores a 64-bit big-endian signed integer
                // +----+--------+--------+--------+--------+--------+--------+--------+--------+
                // |0xd3|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
                // +----+--------+--------+--------+--------+--------+--------+--------+--------+
                l = source.pullInt64()
            }

//            0xcf -> {
//                // uint 64 stores a 64-bit big-endian unsigned integer
//                // +----+--------+--------+--------+--------+--------+--------+--------+--------+
//                // |0xcf|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
//                // +----+--------+--------+--------+--------+--------+--------+--------+--------+
////                l = source.pullUInt64().toLong() // FIXME
//                l = source.pullInt64() // FIXME
//            }

            else -> {
                // TODO maybe restrict to int types here
                source.back()
                l = intTypeUnpacker.unpack(source)!!.toLong()
            }
        }
        return l
    }

}
