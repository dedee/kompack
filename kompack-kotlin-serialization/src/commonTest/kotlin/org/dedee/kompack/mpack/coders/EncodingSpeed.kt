package org.dedee.kompack.mpack.coders

import org.dedee.kompack.mpack.pack.InMemoryPacker
import kotlin.test.Test
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class EncodingSpeed {

    @OptIn(ExperimentalTime::class)
    @Test
    fun measureEncodingSpeed() {
        val book = BookUtil.createBook()
        var t2 = 0L
        repeat(1000) {
            t2 += measureTime {
                MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000))
            }.inWholeMilliseconds
        }

        println("Encoding speed 1000x book encoding")
        println("----------------------------------")
        println("org.dedee.kompack.mpack.coders.MessagePackEncoder:        ${t2} ms")

        // JAVA
//        Encoding speed 1000x book encoding
//                ----------------------------------
//                org.dedee.kompack.mpack.coders.MessagePackEncoder:        96 ms

        // NATIVE
//        Encoding speed 1000x book encoding
//                ----------------------------------
//                org.dedee.kompack.mpack.coders.MessagePackEncoder:        4026 ms XXXX !!!
    }

}
