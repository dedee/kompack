package com.wunderbee.kompack.testsuite.performance

import com.wunderbee.kompack.mpack.unpack.InMemoryUnpacker
import org.junit.jupiter.api.Test
import kotlin.system.measureNanoTime

class `Unpacker Performance Tests` {

    @Test
    fun `Direct API`() {
        // Fastest: Direct call
        repeat(1000) {
            InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpackInt()
        }
        val t = measureNanoTime {
            repeat(10_000) {
                InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpackInt()
            }
        }
        println("Direct API $t")
    }

    @Test
    fun `Map based`() {
        // Second: Use Map
        repeat(1000) {
            InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk2<Int>()
        }
        val t = measureNanoTime {
            repeat(10_000) {
                InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk2<Int>()
            }
        }
        println("MAP based $t")
    }

    @Test
    fun `Reified fun`() {
        // 3rd
        repeat(1000) {
            InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk<Int>()
        }
        val t = measureNanoTime {
            repeat(10_000) {
                InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk<Int>()
            }
        }
        println("Reified $t")
    }

}
