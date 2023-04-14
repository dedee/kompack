package org.dedee.kompack.mpack.pack

class LongTypePacker : TypePacker<Long> {

//    private val intTypePacker = IntTypePacker()
    override fun pack(obj: Long, sink: Sink) {
        packLong(obj, sink)
    }

    private fun packLong(obj: Long, sink: Sink) {
//        if (obj < Int.MIN_VALUE) {
            packInt64(obj, sink)
//        } else if (obj > Int.MAX_VALUE) {
//            packUInt64(obj, sink)
//        } else {
//            // fits into int
//            intTypePacker.packInt(obj.toInt(), sink)
//        }
    }


//    private fun packUInt64(d: ULong, sink: Sink) {
//        // uint 64 stores a 64-bit big-endian unsigned integer
//        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
//        // |  0xcf  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
//        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
//        sink.addByte(0xcf)
//        sink.addUInt64(d)
//    }

    private fun packInt64(d: Long, sink: Sink) {
        // int 64 stores a 64-bit big-endian signed integer
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // |  0xd3  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        sink.addByte(0xd3)
        sink.addInt64(d)
    }
}


