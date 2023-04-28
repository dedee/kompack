package org.dedee.kompack.mpack.unpack

import java.io.InputStream

class StreamUnpacker(private val inputStream: InputStream) : Unpacker(
    Source(
        org.dedee.kompack.mpack.util.BufferSourceInputStream(inputStream)
    )
),
    AutoCloseable {
    override fun close() {
        inputStream.close()
    }

}