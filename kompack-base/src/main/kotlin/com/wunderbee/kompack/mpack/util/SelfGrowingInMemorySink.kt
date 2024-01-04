package com.wunderbee.kompack.mpack.util

import com.wunderbee.kompack.mpack.pack.Sink

class SelfGrowingInMemorySink(size: Int = 1024, val regrowExtendSize: Int = 1024) : Sink {

    var length: Int = 0
    var dest = ByteArray(size)

    override fun addByte(x: Int): SelfGrowingInMemorySink {
        request(1)
        length += dest.encodeByte(length, x)
        return this
    }

    override fun addInt16(x: Int): Sink {
        request(2)
        length += dest.encodeInt16(length, x)
        return this
    }

    override fun addInt32(x: Int): Sink {
        request(4)
        length += dest.encodeInt32(length, x)
        return this
    }

    override fun addInt64(x: Long): Sink {
        request(8)
        length += dest.encodeInt64(length, x)
        return this
    }

    override fun addUInt64(x: ULong): Sink {
        request(8)
        length += dest.encodeInt64(length, x.toLong())
        return this
    }

    override fun addBytes(src: ByteArray, offset: Int, len: Int): Sink {
        request(len)
        length += dest.encodeBytes(length, src, offset, len)
        return this
    }

    override fun addBytes(src: ByteArray): Sink {
        request(src.size)
        length += dest.encodeBytes(length, src, 0, src.size)
        return this
    }

    override fun flush() {
        // Noting to flush in memory
    }

    inline fun request(numberOfBytes: Int) {
        if ((length + numberOfBytes) >= dest.size) {
            // extend
            val b = ByteArray(length + regrowExtendSize)
            dest.copyInto(b, 0, 0, length)
            dest = b
        }
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