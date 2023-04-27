package org.dedee.kompack.testsuite.stresstests

import org.dedee.kompack.mpack.coders.MessagePackEncoder
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream

class SerializationStressTests {

    init {
        val book = BookUtil.createBook()

        val f = File.createTempFile("SerializationStressTests", ".dat")
        println(f)
        try {

            BufferedOutputStream(FileOutputStream(f)).use { out ->
                repeat(CYCLES) {
                    MessagePackEncoder.encodeToStream(book, out)
                }
                out.flush()
                println(f.length())
            }

        } finally {
            f.delete()
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