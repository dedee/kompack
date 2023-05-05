package com.wunderbee.kompack.mpack.pack

class FloatTypePacker : TypePacker<Float> {
    override fun pack(obj: Float, sink: Sink) {
        sink.addByte(0xca)
        sink.addInt32(obj.toBits())
    }
}