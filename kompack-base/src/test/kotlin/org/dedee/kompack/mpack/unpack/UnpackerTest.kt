package org.dedee.kompack.mpack.unpack

import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.SinkInMemory
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class UnpackerTest {

    @Test
    fun `Unpack int NIL`() {
        assertNull(Unpacker(Source(byteArrayOf(0xc0.toByte()))).unpackInt())
    }

    @Test
    fun `Unpack int 1`() {
        assertEquals(1, Unpacker(Source(byteArrayOf(0x01.toByte()))).unpackInt())
    }

    @Test
    fun `Unpack long NIL`() {
        assertNull(Unpacker(Source(byteArrayOf(0xc0.toByte()))).unpackLong())
    }

    @Test
    fun foo() {
        val dest = ByteArray(100)
        val s = Packer(SinkInMemory(dest))

        val t = listOf(0, 5, -5, 0xff, -0xff, 0x7fff, 255, 256, 127, 128, -1, -2, Int.MIN_VALUE, Int.MAX_VALUE)

        // Int.MAX_VALUE // 7FFFFFFF
        // UInt.MAX_VALUE

        t.forEach {
            s.pack(it)
        }

//        val d = Unpacker(Source(s.build()))
//        t.forEach {
//            assertEquals(it, d.unpackInt())
//        }
    }

    @Test
    fun reifiedTests() {
//        val j:Int= Unpacker(Source(byteArrayOf(0x12.toByte()))).unpackk()
        val i: Int? = Unpacker(Source(byteArrayOf(0x12.toByte()))).unpakk()
        println(i)
        val j: Long? = Unpacker(Source(byteArrayOf(0x12.toByte()))).unpakk()
        println(j)
    }

}