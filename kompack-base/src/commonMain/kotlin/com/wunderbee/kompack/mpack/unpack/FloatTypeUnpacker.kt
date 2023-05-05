package com.wunderbee.kompack.mpack.unpack

class FloatTypeUnpacker : TypeUnpacker<Float> {

    override fun unpack(source: Source): Float? {
        when (val type = source.pullByte()) {
            0xc0 -> {
                // nil:
                // +------+
                // | 0xc0 |
                // +------+
                return null
            }

            0xca -> {
                // float 32 stores a floating point number in IEEE 754 single precision floating point number format:
                //+--------+--------+--------+--------+--------+
                //|  0xca  |XXXXXXXX|XXXXXXXX|XXXXXXXX|XXXXXXXX|
                //+--------+--------+--------+--------+--------+
                return Float.fromBits(source.pullInt32())
            }

            else -> {
                throw Exception("Could not unpack, unknown type $type")
            }
        }
    }

}
