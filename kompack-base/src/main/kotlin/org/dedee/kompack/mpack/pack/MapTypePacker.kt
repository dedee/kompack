package org.dedee.kompack.mpack.pack

class MapTypePacker : TypePacker<Map<Any, Any?>> {

    override fun pack(obj: Map<Any, Any?>, sink: Sink) {
        val packer = Packer(sink)
        packMapBegin(obj.size, sink)
        obj.forEach { (t, u) ->
            packer.pack(t)
            packer.pack(u)
        }
    }

    private fun packMapBegin(size: Int, sink: Sink) {
        if (size < 16) {
            sink.addByte(0x80 or size)
        } else if (size < 65536) {
            sink.addByte(0xde)
            sink.addInt16(size)
        } else {
            sink.addByte(0xdf)
            sink.addInt32(size)
        }
    }

}