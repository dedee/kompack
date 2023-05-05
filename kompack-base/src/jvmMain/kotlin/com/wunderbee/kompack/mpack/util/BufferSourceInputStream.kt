package com.wunderbee.kompack.mpack.util

import java.io.InputStream

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