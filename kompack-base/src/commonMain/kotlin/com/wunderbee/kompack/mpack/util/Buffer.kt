package com.wunderbee.kompack.mpack.util

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
//        assert(pos < data.size)
        return data[pos++].toInt()
    }

    override fun pushBack() {
        pos--
        if (pos < 0) {
            throw Exception("Pushed back too often")
        }
    }

}


