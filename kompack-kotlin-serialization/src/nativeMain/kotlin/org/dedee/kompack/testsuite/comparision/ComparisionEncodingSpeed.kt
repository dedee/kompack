package org.dedee.kompack.testsuite.comparision

import org.dedee.kompack.mpack.coders.MessagePackEncoder
import org.dedee.kompack.mpack.pack.InMemoryPacker
import kotlin.system.measureTimeMillis

class ComparisionEncodingSpeed {

    init {
        val book = BookUtil.createBook()

        var t1 = 0L
        var t2 = 0L
        var t3 = 0L

        repeat(1000) {

//            t1 += measureTimeMillis {
//                MsgPack.encodeToByteArray(book)
//            }

            t2 += measureTimeMillis {
                MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000))
            }

//            t3 += measureTimeMillis {
//                Json.encodeToString(book)
//            }
        }

        println("Encoding speed 1000x book encoding")
        println("----------------------------------")
        println("com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack:  ${t1} ms")
        println("org.dedee.kompack.mpack.coders.MessagePackEncoder:        ${t2} ms")
        println("kotlinx.serialization.json.Json:                          ${t3} ms")
    }

//    companion object {
//
//        @JvmStatic
//        fun main(args: Array<String>) {
//            ComparisionEncodingSpeed()
//        }
//    }
}