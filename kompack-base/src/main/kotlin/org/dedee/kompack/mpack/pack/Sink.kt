package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import java.io.OutputStream

interface Sink {
    fun addByte(x: Int)
    fun addInt16(x: Int)
    fun addInt32(x: Int)
    fun addInt64(x: Long)
    fun addUInt64(x: ULong)
    fun addBytes(src: ByteArray, offset: Int, len: Int)
    fun addBytes(src: ByteArray)
    fun build(): ByteArray
    fun toHexString(): String
}

class SinkInMemory(private val dest: ByteArray) : Sink {

    private var length: Int = 0

    override fun addByte(x: Int) {
        dest[length++] = (x and 0xff).toByte()
    }

    override fun addInt16(x: Int) {
        dest[length++] = (x ushr 8 and 0xff).toByte()
        dest[length++] = (x and 0xff).toByte()
    }

    override fun addInt32(x: Int) {
        dest[length++] = (x ushr 24 and 0xff).toByte()
        dest[length++] = (x ushr 16 and 0xff).toByte()
        dest[length++] = (x ushr 8 and 0xff).toByte()
        dest[length++] = (x and 0xff).toByte()
    }

    override fun addInt64(x: Long) {
        dest[length++] = (x ushr 56 and 0xffL).toByte()
        dest[length++] = (x ushr 48 and 0xffL).toByte()
        dest[length++] = (x ushr 40 and 0xffL).toByte()
        dest[length++] = (x ushr 32 and 0xffL).toByte()
        dest[length++] = (x ushr 24 and 0xffL).toByte()
        dest[length++] = (x ushr 16 and 0xffL).toByte()
        dest[length++] = (x ushr 8 and 0xffL).toByte()
        dest[length++] = (x and 0xffL).toByte()
    }

    override fun addUInt64(x: ULong) {
        addInt64(x.toLong())
    }

    override fun addBytes(src: ByteArray, offset: Int, len: Int) {
        System.arraycopy(src, offset, this.dest, length, len)
        length += len
    }

    override fun addBytes(src: ByteArray) {
        addBytes(src, 0, src.size)
    }

    override fun build(): ByteArray {
        val l = length
        length = 0
        return dest.copyOf(l)
    }

    override fun toHexString(): String {
        return build().hex()
    }
}

class SinkStream(private val out: OutputStream) : Sink {

    override fun addByte(x: Int) {
        out.write(x)
    }

    override fun addInt16(x: Int) {
        out.write(x ushr 8 and 0xff)
        out.write(x and 0xff)
    }

    override fun addInt32(x: Int) {
        out.write(x ushr 24 and 0xff)
        out.write(x ushr 16 and 0xff)
        out.write(x ushr 8 and 0xff)
        out.write(x and 0xff)
    }

    override fun addInt64(x: Long) {
        out.write((x ushr 56 and 0xff).toInt())
        out.write((x ushr 48 and 0xff).toInt())
        out.write((x ushr 40 and 0xff).toInt())
        out.write((x ushr 32 and 0xff).toInt())
        out.write((x ushr 24 and 0xff).toInt())
        out.write((x ushr 16 and 0xff).toInt())
        out.write((x ushr 8 and 0xff).toInt())
        out.write((x and 0xff).toInt())
    }

    override fun addUInt64(x: ULong) {
        addInt64(x.toLong())
    }

    override fun addBytes(src: ByteArray, offset: Int, len: Int) {
        out.write(src, offset, len)
    }

    override fun addBytes(src: ByteArray) {
        out.write(src)
    }

    override fun build(): ByteArray {
        TODO("Not yet implemented")
    }

    override fun toHexString(): String {
        TODO("Not yet implemented")
    }

}