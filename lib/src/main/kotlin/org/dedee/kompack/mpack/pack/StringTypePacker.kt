package org.dedee.kompack.mpack.pack

class StringTypePacker : TypePacker<String> {
    override fun pack(obj: String, sink: Sink) {
        packString(obj, sink)
    }


    fun packString(s: String, sink: Sink) {
        val b = s.encodeToByteArray()
        packStringLength(b.size, sink)
        sink.addBytes(b)
    }

    private fun packStringLength(len: Int, sink: Sink) {
        // XXXXX is a 5-bit unsigned integer which represents N
        // YYYYYYYY is a 8-bit unsigned integer which represents N
        // ZZZZZZZZ_ZZZZZZZZ is a 16-bit big-endian unsigned integer which represents N
        // AAAAAAAA_AAAAAAAA_AAAAAAAA_AAAAAAAA is a 32-bit big-endian unsigned integer which represents N
        // N is the length of data
        if (len < 32) {
            // fixstr stores a byte array whose length is upto 31 bytes:
            //+--------+========+
            //|101XXXXX|  data  |
            //+--------+========+
            sink.addByte(0xa0 or len)
        } else if (len < 256) {
            // str 8 stores a byte array whose length is upto 255 bytes:
            //+--------+--------+========+
            //|  0xd9  |YYYYYYYY|  data  |
            //+--------+--------+========+
            sink.addByte(0xd9)
            sink.addByte(len)
        } else if (len < 65536) {
            //str 16 stores a byte array whose length is upto (2^16)-1 bytes:
            //+--------+--------+--------+========+
            //|  0xda  |ZZZZZZZZ|ZZZZZZZZ|  data  |
            //+--------+--------+--------+========+
            sink.addByte(0xda)
            sink.addInt16(len)
        } else {
            //str 32 stores a byte array whose length is upto (2^32)-1 bytes:
            //+--------+--------+--------+--------+--------+========+
            //|  0xdb  |AAAAAAAA|AAAAAAAA|AAAAAAAA|AAAAAAAA|  data  |
            //+--------+--------+--------+--------+--------+========+
            sink.addByte(0xdb)
            sink.addInt32(len)
        }
    }

}
