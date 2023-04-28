package org.dedee.kompack.mpack.pack

class LongTypePacker : TypePacker<Long> {

    override fun pack(obj: Long, sink: Sink) {
        // int 64 stores a 64-bit big-endian signed integer
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // |  0xd3  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        sink.addByte(0xd3)
        sink.addInt64(obj)
    }
}


