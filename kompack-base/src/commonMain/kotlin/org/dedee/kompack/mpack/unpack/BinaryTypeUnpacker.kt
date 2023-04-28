package org.dedee.kompack.mpack.unpack

class BinaryTypeUnpacker : TypeUnpacker<ByteArray> {

    override fun unpack(source: Source): ByteArray? {
        //* XXXXXXXX is a 8-bit unsigned integer which represents N
        //* YYYYYYYY_YYYYYYYY is a 16-bit big-endian unsigned integer which represents N
        //* ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ is a 32-bit big-endian unsigned integer which represents N
        //* N is the length of data

        when (val type = source.pullByte()) {
            0xc0 -> {
                // nil:
                // +------+
                // | 0xc0 |
                // +------+
                return null
            }

            0xc4 -> {
                // bin 8 stores a byte array whose length is upto (2^8)-1 bytes:
                // +--------+--------+========+
                // |  0xc4  |XXXXXXXX|  data  |
                // +--------+--------+========+
                val len = source.pullByte().and(0xff)
                return source.pullBytes(len)
            }

            0xc5 -> {
                // bin 16 stores a byte array whose length is upto (2^16)-1 bytes:
                // +--------+--------+--------+========+
                // |  0xc5  |YYYYYYYY|YYYYYYYY|  data  |
                // +--------+--------+--------+========+
                val len = source.pullInt16()
                return source.pullBytes(len)
            }

            0xc6 -> {
                // FIXME TOO LONG??!! DIFFERENT STREAM BASED API REQUIRED
                // bin 32 stores a byte array whose length is upto (2^32)-1 bytes:
                // +--------+--------+--------+--------+--------+========+
                // |  0xc6  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|  data  |
                // +--------+--------+--------+--------+--------+========+
                val len = source.pullInt32()
                return source.pullBytes(len)
            }

            else -> {
                throw Exception("Could not unpack, unknown type $type")
            }
        }
    }
}