package org.dedee.kompack.mpack.pack

class DoubleTypePacker : TypePacker<Double> {
    override fun pack(obj: Double, sink: Sink) {
        packDouble(obj, sink)
    }
    fun packDouble(d: Double, sink: Sink) {
        sink.addByte(0xcb)
        sink.addInt64(d.toBits())
    }

}
