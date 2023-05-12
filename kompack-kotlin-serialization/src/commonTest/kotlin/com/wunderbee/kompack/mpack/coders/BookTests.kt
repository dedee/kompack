package com.wunderbee.kompack.mpack.coders

import com.wunderbee.kompack.mpack.pack.InMemoryPacker
import com.wunderbee.kompack.mpack.util.SelfGrowingInMemorySink
import kotlin.test.Test
import kotlin.test.assertEquals

class BookTests {


    @Test
    fun `Book sample in memory sink`() {
        val book = BookUtil.createBook()
        val b = MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000))
        val book2 = MessagePackDecoder.decodeFromByteArray<Book>(b)
        assertEquals(book, book2)
    }

    @Test
    fun `Book sample stream sink`() {
        val book = BookUtil.createBook()
        val out = SelfGrowingInMemorySink(1_000_000)
        MessagePackEncoder.encodeToStream(book, out)
        val book2 = MessagePackDecoder.decodeFromByteArray<Book>(out.build())
        assertEquals(book, book2)
    }


}