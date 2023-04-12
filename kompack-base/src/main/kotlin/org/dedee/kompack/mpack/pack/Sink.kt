package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex

class Sink(private val dest: ByteArray) {

    private var length: Int = 0

    fun addByte(x: Int) {
        dest[length++] = (x and 0xff).toByte()
    }

    fun addInt16(x: Int) {
        dest[length++] = (x ushr 8 and 0xff).toByte()
        dest[length++] = (x and 0xff).toByte()
    }

    fun addInt32(x: Int) {
        dest[length++] = (x ushr 24 and 0xff).toByte()
        dest[length++] = (x ushr 16 and 0xff).toByte()
        dest[length++] = (x ushr 8 and 0xff).toByte()
        dest[length++] = (x and 0xff).toByte()
    }

    fun addInt64(x: Long) {
        dest[length++] = (x ushr 56 and 0xffL).toByte()
        dest[length++] = (x ushr 48 and 0xffL).toByte()
        dest[length++] = (x ushr 40 and 0xffL).toByte()
        dest[length++] = (x ushr 32 and 0xffL).toByte()
        dest[length++] = (x ushr 24 and 0xffL).toByte()
        dest[length++] = (x ushr 16 and 0xffL).toByte()
        dest[length++] = (x ushr 8 and 0xffL).toByte()
        dest[length++] = (x and 0xffL).toByte()
    }

    fun addBytes(src: ByteArray, offset: Int, len: Int) {
        System.arraycopy(src, offset, this.dest, length, len)
        length += len
    }

    fun addBytes(src: ByteArray) {
        addBytes(src, 0, src.size)
    }

    fun build(): ByteArray {
        val l = length
        length = 0
        return dest.copyOf(l)
    }

    fun toHexString(): String {
        return build().hex()
    }
}