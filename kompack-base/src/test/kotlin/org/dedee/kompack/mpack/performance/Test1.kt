package org.dedee.kompack.mpack.performance

import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import kotlin.system.measureNanoTime

class Test1 {
    init {
        repeat(1000) {
            InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk<Int>()
            InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk2<Int>()
            InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpackInt()
        }

        // Fastest: Direct call
        val t2 = measureNanoTime {
            repeat(10_000) {
                InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpackInt()
            }
        }

        println(t2)


        // Second: Use Map
        val t3 = measureNanoTime {
            repeat(10_000) {
                InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk2<Int>()
            }
        }
        println(t3)


        // 3rd
        val t1 = measureNanoTime {
            repeat(10_000) {
                InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk<Int>()
            }
        }
        println(t1)

    }
}

fun main() {

    Test1()

}