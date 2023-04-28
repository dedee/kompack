package org.dedee.kompack.mpack.pack

class BooleanTypePacker : TypePacker<Boolean> {
    override fun pack(obj: Boolean, sink: Sink) {
        // false:
        // +--------+
        // |  0xc2  |
        // +--------+
        //
        // true:
        // +--------+
        // |  0xc3  |
        // +--------+
        sink.addByte(if (obj) 0xc3 else 0xc2)
    }
}

