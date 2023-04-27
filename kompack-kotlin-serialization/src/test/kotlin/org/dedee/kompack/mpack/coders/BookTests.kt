package org.dedee.kompack.mpack.coders

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import kotlin.system.measureTimeMillis

class BookTests {


    @Test
    fun `Book sample in memory sink`() {
        val book = BookUtil.createBook()
        val nanos = measureTimeMillis {
            val b = MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000))
            val book2 = MessagePackDecoder.decodeFromByteArray<Book>(b)
            assertEquals(book, book2)
        }
        println("Duration: $nanos ms")
    }

    @Test
    fun `Book sample stream sink`() {
        val book = BookUtil.createBook()
        val nanos = measureTimeMillis {
            val out = ByteArrayOutputStream()
            MessagePackEncoder.encodeToStream(book, out)
            val book2 = MessagePackDecoder.decodeFromByteArray<Book>(out.toByteArray())
            assertEquals(book, book2)
        }
        println("Duration: $nanos ms")
    }


}