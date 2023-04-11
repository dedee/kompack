package org.dedee.kompack.mpack.pack

class FloatTypePacker : TypePacker<Float> {
    override fun pack(obj: Float, sink: Sink) {
        packFloat(obj, sink)
    }

    fun packFloat(f: Float, sink: Sink) {
        sink.addByte(0xca)
        sink.addInt32(f.toBits())
    }
}