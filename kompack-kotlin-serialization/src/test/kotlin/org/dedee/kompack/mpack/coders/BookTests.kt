package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.Sink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
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
    fun `Book sample`() {
        val book = BookUtil.createBook()
        val packer = Packer(Sink(ByteArray(1_000_000)))
        val nanos = measureTimeMillis {
            val b = MessagePackEncoder.encodeToByteArray(book, packer)
            println("Encoded book to ${b.size} bytes")
            val book2 = MessagePackDecoder.decodeFromByteArray<Book>(b)
            println("Decoded all again")
            assertEquals(book, book2)
        }
        println("Duration: $nanos ms")
    }


}