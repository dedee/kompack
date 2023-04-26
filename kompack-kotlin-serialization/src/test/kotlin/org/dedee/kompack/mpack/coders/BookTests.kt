package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.SinkInMemory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream
import kotlin.system.measureTimeMillis

class BookTests {

    @Serializable
    data class Book(
        val author: String,
        val isbn: String,
        val chapters: List<Chapter>
    )

    @Serializable
    data class Chapter(
        val title: String,
        val paragraphs: List<String>,
    )

    @Test
    fun `Book sample in memory sink`() {
        val book = BookUtil.createBook()
        val nanos = measureTimeMillis {
            val b = MessagePackEncoder.encodeToByteArray(book, Packer(SinkInMemory(ByteArray(1_000_000))))
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