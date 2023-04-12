package org.dedee.kompack.mpack.unpack

class BooleanTypeUnpacker : TypeUnpacker<Boolean> {

    override fun unpack(source: Source): Boolean? {
        when (source.pullByte()) {
            0xc0 -> {
                // nil:
                // +------+
                // | 0xc0 |
                // +------+
                return null
            }

            0xc2 -> {
                // false:
                //+--------+
                //|  0xc2  |
                //+--------+
                return false
            }

            0xc3 -> {
                //true:
                //+--------+
                //|  0xc3  |
                //+--------+
                return true
            }

            else -> {
                throw Exception() // FIXME
            }
        }
    }


}