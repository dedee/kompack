package org.dedee.kompack.mpack.unpack

import org.dedee.kompack.mpack.util.BufferSource

class Source(private val src: BufferSource) {
    fun pullByte(): Int {
        return src.pullUByte()
    }

    fun pullInt16(): Int {
        return (src.pullSByte() shl 8) or (src.pullUByte())
    }
   
    fun pullInt32(): Int {
        return (src.pullSByte() shl 24) or (src.pullUByte() shl 16) or (src.pullUByte() shl 8) or (src.pullUByte())
    }

    fun pullInt64(): Long {
        return (src.pullSByte().toLong() shl 56) or
                (src.pullUByte().toLong() shl 48) or
                (src.pullUByte().toLong() shl 40) or
                (src.pullUByte().toLong() shl 32) or
                (src.pullUByte().toLong() shl 24) or
                (src.pullUByte().toLong() shl 16) or
                (src.pullUByte().toLong() shl 8) or
                (src.pullUByte().toLong())
    }

    fun pullUInt64(): ULong {
        return (src.pullUByte().toULong() shl 56) or
                (src.pullUByte().toULong() shl 48) or
                (src.pullUByte().toULong() shl 40) or
                (src.pullUByte().toULong() shl 32) or
                (src.pullUByte().toULong() shl 24) or
                (src.pullUByte().toULong() shl 16) or
                (src.pullUByte().toULong() shl 8) or
                (src.pullUByte().toULong())
    }

    fun back() {
        src.pushBack()
    }

    fun pullBytes(len: Int): ByteArray {
        val d = ByteArray(len)
        for (i in 0 until len) {
            d[i] = src.pullUByte().toByte()
        }
        return d
    }
}
