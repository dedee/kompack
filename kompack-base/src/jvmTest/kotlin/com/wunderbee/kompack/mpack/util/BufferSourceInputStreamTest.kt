package com.wunderbee.kompack.mpack.util

import java.io.ByteArrayInputStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BufferSourceInputStreamTest {

    @Test
    fun pull() {
        val ins = BufferSourceInputStream(ByteArrayInputStream(byteArrayOf(0, 1, 2, 3, 4, 5)))
        assertEquals(0, ins.pullSByte())
        assertEquals(1, ins.pullSByte())
        assertEquals(2, ins.pullSByte())
        assertEquals(3, ins.pullSByte())
        assertEquals(4, ins.pullSByte())
        assertEquals(5, ins.pullSByte())
        assertFailsWith<Exception> { ins.pullSByte() }
    }

    @Test
    fun pullAndPushBack() {
        val ins = BufferSourceInputStream(ByteArrayInputStream(byteArrayOf(0, 1, 2, 3, 4, 5)))
        assertEquals(0, ins.pullSByte())
        ins.pushBack()
        assertEquals(0, ins.pullSByte())
        assertEquals(1, ins.pullSByte())
        assertEquals(2, ins.pullSByte())
        assertEquals(3, ins.pullSByte())
        assertEquals(4, ins.pullSByte())
        assertEquals(5, ins.pullSByte())
        ins.pushBack()
        assertEquals(5, ins.pullSByte())
        assertFailsWith<Exception> { ins.pullSByte() }
    }

    @Test
    fun pullAndPushBackTwice() {
        val ins = BufferSourceInputStream(ByteArrayInputStream(byteArrayOf(0, 1, 2, 3, 4, 5)))
        assertEquals(0, ins.pullSByte())
        ins.pushBack()
        assertFailsWith<AssertionError> { ins.pushBack() }
    }

    @Test
    fun pullSigned() {
        val ins = BufferSourceInputStream(ByteArrayInputStream(byteArrayOf(0, -1, -2, -3, -4, -5)))
        assertEquals(0, ins.pullSByte())
        assertEquals(-1, ins.pullSByte())
        assertEquals(-2, ins.pullSByte())
        assertEquals(-3, ins.pullSByte())
        assertEquals(-4, ins.pullSByte())
        assertEquals(-5, ins.pullSByte())
        assertFailsWith<Exception> { ins.pullSByte() }
    }

    @Test
    fun pullUSigned() {
        val ins = BufferSourceInputStream(ByteArrayInputStream(byteArrayOf(0, -1, -2, -3, -4, -5)))
        assertEquals(0, ins.pullUByte())
        assertEquals(255, ins.pullUByte())
        assertEquals(254, ins.pullUByte())
        assertEquals(253, ins.pullUByte())
        assertEquals(252, ins.pullUByte())
        assertEquals(251, ins.pullUByte())
        assertFailsWith<Exception> { ins.pullSByte() }
    }

}