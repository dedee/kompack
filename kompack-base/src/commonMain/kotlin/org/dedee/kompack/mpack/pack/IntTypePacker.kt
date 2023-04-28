package org.dedee.kompack.mpack.pack

class IntTypePacker : TypePacker<Int> {
    override fun pack(obj: Int, sink: Sink) {
        if (obj < -32) {
            if (obj < -32768) {
                packInt32(obj, sink)
            } else if (obj < -128) {
                packInt16(obj, sink)
            } else {
                packInt8(obj, sink)
            }
        } else if (obj < 128) {
            packSmallestByte(obj, sink)
        } else {
            if (obj <= 255) {
                packUInt8(obj, sink)
            } else if (obj <= 65535) {
                packUInt16(obj, sink)
            } else {
                packUInt32(obj, sink)
            }
        }
    }


    fun packSmallestByte(d: Int, sink: Sink) {
        // positive fixint stores 7-bit positive integer
        // +--------+
        // |0XXXXXXX|
        // +--------+
        //
        // negative fixint stores 5-bit negative integer
        // +--------+
        // |111YYYYY|
        // +--------+
        sink.addByte(d)
    }

    fun packInt8(d: Int, sink: Sink) {
        // int 8 stores a 8-bit signed integer
        // +--------+--------+
        // |  0xd0  |ZZZZZZZZ|
        // +--------+--------+
        sink.addByte(0xd0)
        sink.addByte(d)
    }

    fun packUInt8(d: Int, sink: Sink) {
        // uint 8 stores a 8-bit unsigned integer
        // +--------+--------+
        // |  0xcc  |ZZZZZZZZ|
        // +--------+--------+
        sink.addByte(0xcc)
        sink.addByte(d)

    }

    fun packInt16(d: Int, sink: Sink) {
        // int 16 stores a 16-bit big-endian signed integer
        // +--------+--------+--------+
        // |  0xd1  |ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+
        sink.addByte(0xd1)
        sink.addInt16(d)
    }

    fun packUInt16(d: Int, sink: Sink) {
        // uint 16 stores a 16-bit big-endian unsigned integer
        // +--------+--------+--------+
        // |  0xcd  |ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+
        sink.addByte(0xcd)
        sink.addInt16(d)
    }

    fun packInt32(d: Int, sink: Sink) {
        // int 32 stores a 32-bit big-endian signed integer
        // +--------+--------+--------+--------+--------+
        // |  0xd2  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+
        sink.addByte(0xd2)
        sink.addInt32(d)
    }

    fun packUInt32(d: Int, sink: Sink) {
        // uint 32 stores a 32-bit big-endian unsigned integer
        // +--------+--------+--------+--------+--------+
        // |  0xce  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+
        sink.addByte(0xce)
        sink.addInt32(d)
    }
}
