package com.wunderbee.kompack.mpack.unpack

import java.io.InputStream

class StreamUnpacker(private val inputStream: InputStream) : Unpacker(
    Source(
        com.wunderbee.kompack.mpack.util.BufferSourceInputStream(inputStream)
    )
),
    AutoCloseable {
    override fun close() {
        inputStream.close()
    }

}