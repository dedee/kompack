package org.dedee.kompack.mpack.unpack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UnpackerTest {

    @Test
    fun `Unpack NIL`() {
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
    fun `Unpack wrong format`() {
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackInt()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackLong()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackULong()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackString()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackDouble()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackFloat()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackArray()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackBinary()) }
        assertThrows<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackMap()) }
    }

    @Test
    fun `Unpack int 1`() {
        assertEquals(1, InMemoryUnpacker(byteArrayOf(0x01.toByte())).unpackInt())
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