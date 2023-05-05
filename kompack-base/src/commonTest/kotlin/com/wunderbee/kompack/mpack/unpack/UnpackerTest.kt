package com.wunderbee.kompack.mpack.unpack

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

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
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackInt()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackLong()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackULong()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackString()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackDouble()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackFloat()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackArray()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackBinary()) }
        assertFailsWith<Exception> { (InMemoryUnpacker(byteArrayOf(0xc1.toByte())).unpackMap()) }
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