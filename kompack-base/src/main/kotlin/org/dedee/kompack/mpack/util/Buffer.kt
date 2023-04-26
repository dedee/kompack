package org.dedee.kompack.mpack.util

import java.io.InputStream

interface BufferSource {
    fun pullUByte(): Int
    fun pullSByte(): Int
    fun pushBack()
}

class BufferSourceMemory(private val data: ByteArray) : BufferSource {
    private var pos = 0
    override fun pullUByte(): Int {
        return pullSByte() and 0xff
    }

    override fun pullSByte(): Int {
        assert(pos < data.size)
        return data[pos++].toInt()
    }

    override fun pushBack() {
        pos--
        assert(pos >= 0)
    }

}

class BufferSourceInputStream(private val ins: InputStream) : BufferSource {

    private var last: Int? = null
    private var b: Int? = null
    override fun pullUByte(): Int {
        return pullSByte() and 0xff
    }

    override fun pullSByte(): Int {
        if (b != null) {
            last = b
            b = null
        } else {
            val r = ins.read()
            if (r < 0) {
                throw Exception("Stream end")
            }
            last = r
        }
        return last!!
    }

    override fun pushBack() {
        assert(b == null)
        assert(last != null)
        b = last
        last = null
    }


}

