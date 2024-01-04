package com.wunderbee.kompack.testsuite.comparision

import com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack
import com.wunderbee.kompack.mpack.coders.MessagePackEncoder
import com.wunderbee.kompack.mpack.pack.InMemoryPacker
import com.wunderbee.kompack.testsuite.stresstests.BookUtil
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.jupiter.api.Test
import kotlin.system.measureTimeMillis

class ComparisionEncodingSpeed {

    @Test
    fun compareEncodingSpeed() {
        val book = BookUtil.createBook()

        var t1 = 0L
        var t2 = 0L
        var t3 = 0L

        repeat(1000) {

            t1 += measureTimeMillis {
                MsgPack.encodeToByteArray(book)
            }

            t2 += measureTimeMillis {
                MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000))
            }

            t3 += measureTimeMillis {
                Json.encodeToString(book)
            }
        }

        println("Encoding speed 1000x book encoding")
        println("----------------------------------")
        println("com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack:  ${t1} ms")
        println("com.wunderbee.kompack.mpack.coders.MessagePackEncoder:    ${t2} ms")
        println("kotlinx.serialization.json.Json:                          ${t3} ms")
    }

}