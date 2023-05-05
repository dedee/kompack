package com.wunderbee.kompack.testsuite.stresstests

import com.wunderbee.kompack.mpack.coders.MessagePackEncoder
import com.wunderbee.kompack.mpack.pack.SinkStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class SerializationStressTests {

    init {
        val book = BookUtil.createBook()

        val f = File.createTempFile("SerializationStressTests", ".dat")
        println(f)
        try {

            SinkStream(BufferedOutputStream(FileOutputStream(f))).use { out ->
                repeat(CYCLES) {
                    MessagePackEncoder.encodeToStream(book, out)
                }
                out.flush()
                println(f.length())
            }

        } finally {
            assert(f.delete())
        }
    }

    companion object {
        val CYCLES = 10000

        @JvmStatic
        fun main(args: Array<String>) {
            repeat(CYCLES) { SerializationStressTests() }
        }
    }
}