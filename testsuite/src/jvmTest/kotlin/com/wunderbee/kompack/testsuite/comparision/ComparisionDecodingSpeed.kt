package com.wunderbee.kompack.testsuite.comparision

import com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack
import com.wunderbee.kompack.mpack.coders.MessagePackDecoder
import com.wunderbee.kompack.mpack.coders.MessagePackEncoder
import com.wunderbee.kompack.mpack.pack.InMemoryPacker
import com.wunderbee.kompack.testsuite.stresstests.Book
import com.wunderbee.kompack.testsuite.stresstests.BookUtil
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class ComparisionDecodingSpeed {

    @Test
    fun compareDecodingSpeed() {
        val book = BookUtil.createBook()
        val flat1 = MsgPack.encodeToByteArray(book)
        val flat2 = MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000))
        val s = Json.encodeToString(book)

        var t1 = 0L
        var t2 = 0L
        var t3 = 0L

        repeat(1000) {

            t1 += measureTimeMillis {
                MsgPack.decodeFromByteArray<Book>(flat1)
            }

            t2 += measureTimeMillis {
                MessagePackDecoder.decodeFromByteArray<Book>(flat2)
            }

            t3 += measureTimeMillis {
                Json.decodeFromString<Book>(s)
            }
        }

        println("Decoding speed 1000x book decoding")
        println("----------------------------------")
        println("com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack:  ${t1} ms")
        println("com.wunderbee.kompack.mpack.coders.MessagePackDecoder:    ${t2} ms")
        println("kotlinx.serialization.json.Json:                          ${t3} ms")
    }

}