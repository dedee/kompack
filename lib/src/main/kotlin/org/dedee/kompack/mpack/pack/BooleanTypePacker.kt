package org.dedee.kompack.mpack.pack

class BooleanTypePacker : TypePacker<Boolean> {
    override fun pack(obj: Boolean, sink: Sink) {
        packBoolean(obj, sink)
    }

    fun packBoolean(b: Boolean, sink: Sink) {
        // false:
        // +--------+
        // |  0xc2  |
        // +--------+
        //
        // true:
        // +--------+
        // |  0xc3  |
        // +--------+
        sink.addByte(if (b) 0xc3 else 0xc2)
    }
}

