package com.wunderbee.kompack.mpack.pack

class ArrayTypePacker : TypePacker<Array<Any>> {

    override fun pack(obj: Array<Any>, sink: Sink) {
        val packer = Packer(sink)
        packArrayStart(obj.size, sink)
        obj.forEach {
            packer.pack(it)
        }
    }

    private fun packArrayStart(size: Int, sink: Sink) {
        if (size < 16) {
            sink.addByte(0x90 or size)
        } else if (size < 65536) {
            sink.addByte(0xdc)
            sink.addInt16(size)
        } else {
            sink.addByte(0xdd)
            sink.addInt32(size)
        }
    }

}

