package org.dedee.kompack.mpack.pack

class MapSerializer {

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