package org.dedee.kompack.mpack.unpack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

class UnpackerTest {

    @Test
    fun `Unpack int NIL`() {
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackInt())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackLong())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackULong())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackString())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackDouble())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackFloat())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackArray())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackBinary())
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackMap())
    }

    @Test
    fun `Unpack int 1`() {
        assertEquals(1, InMemoryUnpacker(byteArrayOf(0x01.toByte())).unpackInt())
    }

    @Test
    fun `Unpack long NIL`() {
        assertNull(InMemoryUnpacker(byteArrayOf(0xc0.toByte())).unpackLong())
    }

    @Test
    fun reifiedTests() {
        val i: Int? = InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk()
        println(i)
        assertEquals(0x12, i)
        val j: Long? = InMemoryUnpacker(byteArrayOf(0x12.toByte())).unpakk()
        println(j)
        assertEquals(0x12, j)
    }

}