package com.wunderbee.kompack.mpack.pack

interface Sink {
    fun addByte(x: Int): Sink
    fun addInt16(x: Int): Sink
    fun addInt32(x: Int): Sink
    fun addInt64(x: Long): Sink
    fun addUInt64(x: ULong): Sink
    fun addBytes(src: ByteArray, offset: Int, len: Int): Sink
    fun addBytes(src: ByteArray): Sink
    fun flush()
}

