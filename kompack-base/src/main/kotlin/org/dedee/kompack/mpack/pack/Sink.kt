package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import java.io.OutputStream

interface Sink {
    fun addByte(x: Int): Sink
    fun addInt16(x: Int): Sink
    fun addInt32(x: Int): Sink
    fun addInt64(x: Long): Sink
    fun addUInt64(x: ULong): Sink
    fun addBytes(src: ByteArray, offset: Int, len: Int): Sink
    fun addBytes(src: ByteArray): Sink
}

class SinkInMemory(private val dest: ByteArray) : Sink {

    private var length: Int = 0

    override fun addByte(x: Int): Sink {
        dest[length++] = (x and 0xff).toByte()
        return this
    }

    override fun addInt16(x: Int): Sink {
        dest[length++] = (x ushr 8 and 0xff).toByte()
        dest[length++] = (x and 0xff).toByte()
        return this
    }

    override fun addInt32(x: Int): Sink {
        dest[length++] = (x ushr 24 and 0xff).toByte()
        dest[length++] = (x ushr 16 and 0xff).toByte()
        dest[length++] = (x ushr 8 and 0xff).toByte()
        dest[length++] = (x and 0xff).toByte()
        return this
    }

    override fun addInt64(x: Long): Sink {
        dest[length++] = (x ushr 56 and 0xffL).toByte()
        dest[length++] = (x ushr 48 and 0xffL).toByte()
        dest[length++] = (x ushr 40 and 0xffL).toByte()
        dest[length++] = (x ushr 32 and 0xffL).toByte()
        dest[length++] = (x ushr 24 and 0xffL).toByte()
        dest[length++] = (x ushr 16 and 0xffL).toByte()
        dest[length++] = (x ushr 8 and 0xffL).toByte()
        dest[length++] = (x and 0xffL).toByte()
        return this
    }

    override fun addUInt64(x: ULong): Sink {
        addInt64(x.toLong())
        return this
    }

    override fun addBytes(src: ByteArray, offset: Int, len: Int): Sink {
        System.arraycopy(src, offset, this.dest, length, len)
        length += len
        return this
    }

    override fun addBytes(src: ByteArray): Sink {
        addBytes(src, 0, src.size)
        return this
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

class SinkStream(private val out: OutputStream) : Sink {

    override fun addByte(x: Int): Sink {
        out.write(x)
        return this
    }

    override fun addInt16(x: Int): Sink {
        out.write(x ushr 8 and 0xff)
        out.write(x and 0xff)
        return this
    }

    override fun addInt32(x: Int): Sink {
        out.write(x ushr 24 and 0xff)
        out.write(x ushr 16 and 0xff)
        out.write(x ushr 8 and 0xff)
        out.write(x and 0xff)
        return this
    }

    override fun addInt64(x: Long): Sink {
        out.write((x ushr 56 and 0xff).toInt())
        out.write((x ushr 48 and 0xff).toInt())
        out.write((x ushr 40 and 0xff).toInt())
        out.write((x ushr 32 and 0xff).toInt())
        out.write((x ushr 24 and 0xff).toInt())
        out.write((x ushr 16 and 0xff).toInt())
        out.write((x ushr 8 and 0xff).toInt())
        out.write((x and 0xff).toInt())
        return this
    }

    override fun addUInt64(x: ULong): Sink {
        addInt64(x.toLong())
        return this
    }

    override fun addBytes(src: ByteArray, offset: Int, len: Int): Sink {
        out.write(src, offset, len)
        return this
    }

    override fun addBytes(src: ByteArray): Sink {
        out.write(src)
        return this
    }

}