package org.dedee.kompack.mpack.unpack

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
    fun reifiedTests() {
        val i: Int? = Unpacker(Source(byteArrayOf(0x12.toByte()))).unpakk()
        println(i)
        assertEquals(0x12, i)
        val j: Long? = Unpacker(Source(byteArrayOf(0x12.toByte()))).unpakk()
        println(j)
        assertEquals(0x12, j)
    }

}