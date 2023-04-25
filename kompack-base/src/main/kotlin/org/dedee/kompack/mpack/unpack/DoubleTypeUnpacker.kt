package org.dedee.kompack.mpack.unpack

class DoubleTypeUnpacker : TypeUnpacker<Double> {

    override fun unpack(source: Source): Double? {
        when (val type = source.pullByte()) {
            0xc0 -> {
                // nil:
                // +------+
                // | 0xc0 |
                // +------+
                return null
            }

            0xcb -> {
                // float 64 stores a floating point number in IEEE 754 double precision floating point number format:
                //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
                //|  0xcb  |YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|
                //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
                return Double.fromBits(source.pullInt64())
            }

            else -> {
                throw Exception("Could not unpack, unknown type $type")
            }
        }
    }
}