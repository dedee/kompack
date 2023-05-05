package com.wunderbee.kompack.mpack.coders

import com.wunderbee.kompack.mpack.pack.InMemoryPacker
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class DecodingSpeed {

    @OptIn(ExperimentalTime::class)
    @Test
    fun measureDecodingSpeed() {
        val book = BookUtil.createBook()
        val flat2 = MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000))
        var t2 = 0L
        // FIXME So slow with native
        repeat(10) {
            t2 += measureTime {
                MessagePackDecoder.decodeFromByteArray<Book>(flat2)
            }.inWholeMilliseconds
        }

        println("Decoding speed 1000x book decoding")
        println("----------------------------------")
        println("com.wunderbee.kompack.mpack.coders.MessagePackEncoder:        ${t2} ms")

        // JVM
//        Decoding speed 1000x book decoding
//                ----------------------------------
//                com.wunderbee.kompack.mpack.coders.MessagePackEncoder:        85 ms
        // NATIVE
        // Decoding speed 1000x book decoding
        // ----------------------------------
        // com.wunderbee.kompack.mpack.coders.MessagePackEncoder:        34595 ms   XXXXXX !!!!
    }
}