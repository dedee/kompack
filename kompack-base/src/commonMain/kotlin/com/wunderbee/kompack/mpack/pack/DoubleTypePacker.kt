package com.wunderbee.kompack.mpack.pack

class DoubleTypePacker : TypePacker<Double> {
    override fun pack(obj: Double, sink: Sink) {
        sink.addByte(0xcb)
        sink.addInt64(obj.toBits())
    }
}
