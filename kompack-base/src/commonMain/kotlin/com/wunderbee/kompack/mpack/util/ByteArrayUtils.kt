package com.wunderbee.kompack.mpack.util


inline fun ByteArray.encodeByte(pos: Int, x: Int): Int {
    this[pos] = (x and 0xff).toByte()
    return 1
}

inline fun ByteArray.encodeInt16(pos: Int, x: Int): Int {
    this[pos] = (x ushr 8 and 0xff).toByte()
    this[pos + 1] = (x and 0xff).toByte()
    return 2
}

inline fun ByteArray.encodeInt32(pos: Int, x: Int): Int {
    this[pos] = (x ushr 24 and 0xff).toByte()
    this[pos + 1] = (x ushr 16 and 0xff).toByte()
    this[pos + 2] = (x ushr 8 and 0xff).toByte()
    this[pos + 3] = (x and 0xff).toByte()
    return 4
}

inline fun ByteArray.encodeInt64(pos: Int, x: Long): Int {
    this[pos + 0] = (x ushr 56 and 0xff).toByte()
    this[pos + 1] = (x ushr 48 and 0xff).toByte()
    this[pos + 2] = (x ushr 40 and 0xff).toByte()
    this[pos + 3] = (x ushr 32 and 0xff).toByte()
    this[pos + 4] = (x ushr 24 and 0xff).toByte()
    this[pos + 5] = (x ushr 16 and 0xff).toByte()
    this[pos + 6] = (x ushr 8 and 0xff).toByte()
    this[pos + 7] = (x and 0xff).toByte()
    return 8
}

inline fun ByteArray.encodeBytes(pos: Int, src: ByteArray, offset: Int, len: Int): Int {
    src.copyInto(this, pos, offset, offset + len)
    return len
}
