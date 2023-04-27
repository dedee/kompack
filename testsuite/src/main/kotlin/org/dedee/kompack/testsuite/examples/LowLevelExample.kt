package org.dedee.kompack.testsuite.examples

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.mpack.pack.build
import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.unpack.StreamUnpacker
import java.io.BufferedInputStream
import java.io.File
import java.io.FileInputStream

class LowLevelExample {

    fun `Lowlevel API in memory`() {
        val byteArray = InMemoryPacker(100)
            .pack(true)
            .pack(10)
            .build()

        File("test1.dat").writeBytes(byteArray)

        val p = InMemoryUnpacker(byteArray)
        val b = p.unpackBoolean() // true
        val i = p.unpackInt() // 10
    }

    fun `Lowlevel API stream based`() {
        StreamUnpacker(BufferedInputStream(FileInputStream("test1.dat"))).use { p ->
            val b = p.unpackBoolean() // true
            val i = p.unpackInt() // 10

            println("$b $i")
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            LowLevelExample().`Lowlevel API in memory`()
            LowLevelExample().`Lowlevel API stream based`()
        }
    }
}