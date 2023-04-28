package org.dedee.kompack.mpack.pack

import java.io.OutputStream

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