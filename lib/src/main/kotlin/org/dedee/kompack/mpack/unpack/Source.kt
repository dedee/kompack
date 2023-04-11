package org.dedee.kompack.mpack.unpack

class Source(private val b: ByteArray) {
    var pos = 0

    fun pullByte(): Int {
        return b[pos++].toInt() and 0xff
    }

    fun pullInt16(): Int {
        var ret = 0
        ret = ret or (b[pos++].toInt() shl 8)
        ret = ret or (b[pos++].toInt() and 0xff)
        return ret
    }


    fun pullInt32(): Int {
        var ret = 0
        ret = ret or (b[pos++].toInt() shl 24)
        ret = ret or (b[pos++].toInt() and 0xff shl 16)
        ret = ret or (b[pos++].toInt() and 0xff shl 8)
        ret = ret or (b[pos++].toInt() and 0xff)
        return ret
    }


    fun pullInt64(): Long {
        var ret: Long = 0
        ret = ret or (b[pos++].toLong() shl 56)
        ret = ret or (b[pos++].toLong() and 0xffL shl 48)
        ret = ret or (b[pos++].toLong() and 0xffL shl 40)
        ret = ret or (b[pos++].toLong() and 0xffL shl 32)
        ret = ret or (b[pos++].toLong() and 0xffL shl 24)
        ret = ret or (b[pos++].toLong() and 0xffL shl 16)
        ret = ret or (b[pos++].toLong() and 0xffL shl 8)
        ret = ret or (b[pos++].toLong() and 0xffL)
        return ret
    }

    fun back() {
        pos--
    }

    fun pullBytes(len: Int): ByteArray {
        val ret = ByteArray(len)
        b.copyInto(ret, 0, pos, pos + len)
        pos += len
        return ret
    }
}