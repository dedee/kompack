package org.dedee.kompack.mpack.unpack

class ULongTypeUnpacker : TypeUnpacker<ULong> {
    override fun unpack(source: Source): ULong? {
        return unpackULong(source)
    }

    private fun unpackULong(source: Source): ULong? {
        return when (source.pullByte()) {
            // nil:
            // +------+
            // | 0xc0 |
            // +------+
            0xc0 -> null

            0xcf -> {
                // uint 64 stores a 64-bit big-endian unsigned integer
                // +----+--------+--------+--------+--------+--------+--------+--------+--------+
                // |0xcf|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
                // +----+--------+--------+--------+--------+--------+--------+--------+--------+
                source.pullUInt64()
            }

            else -> {
                throw Exception()
            }
        }
    }
}