package org.dedee.kompack.mpack.pack

class BinTypePacker : TypePacker<ByteArray> {
    override fun pack(obj: ByteArray, sink: Sink) {
        packBin(obj, sink)
    }


    fun packBin(b: ByteArray, sink: Sink) {
        packBinLength(b.size, sink)
        sink.addBytes(b)
    }

    private fun packBinLength(len: Int, sink: Sink) {
        // XXXXXXXX is a 8-bit unsigned integer which represents N
        // YYYYYYYY_YYYYYYYY is a 16-bit big-endian unsigned integer which represents N
        // ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ is a 32-bit big-endian unsigned integer which represents N
        // N is the length of data
        if (len < 256) {
            // bin 8 stores a byte array whose length is upto (2^8)-1 bytes:
            // +--------+--------+========+
            // |  0xc4  |XXXXXXXX|  data  |
            // +--------+--------+========+
            sink.addByte(0xc4)
            sink.addByte(len)
        } else if (len < 65536) {
            // bin 16 stores a byte array whose length is upto (2^16)-1 bytes:
            // +--------+--------+--------+========+
            // |  0xc5  |YYYYYYYY|YYYYYYYY|  data  |
            // +--------+--------+--------+========+
            sink.addByte(0xc5)
            sink.addInt16(len)
        } else {
            // bin 32 stores a byte array whose length is upto (2^32)-1 bytes:
            // +--------+--------+--------+--------+--------+========+
            // |  0xc6  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|  data  |
            // +--------+--------+--------+--------+--------+========+
            sink.addByte(0xc6)
            sink.addInt32(len)
        }
    }
}
