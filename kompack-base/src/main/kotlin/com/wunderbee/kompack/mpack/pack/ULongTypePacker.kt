package com.wunderbee.kompack.mpack.pack

class ULongTypePacker : TypePacker<ULong> {
    override fun pack(obj: ULong, sink: Sink) {
        // uint 64 stores a 64-bit big-endian unsigned integer
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // |  0xcf  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        sink.addByte(0xcf)
        sink.addUInt64(obj)
    }
}