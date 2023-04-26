package org.dedee.kompack.mpack.util

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BufferSourceMemoryTest {

    @Test
    fun pull() {
        val ins = BufferSourceMemory(byteArrayOf(0, 1, 2, 3, 4, 5))
        assertEquals(0, ins.pullSByte())
        assertEquals(1, ins.pullSByte())
        assertEquals(2, ins.pullSByte())
        assertEquals(3, ins.pullSByte())
        assertEquals(4, ins.pullSByte())
        assertEquals(5, ins.pullSByte())
        assertThrows<AssertionError> { ins.pullSByte() }
    }

    @Test
    fun pullAndPushBack() {
        val ins = BufferSourceMemory(byteArrayOf(0, 1, 2, 3, 4, 5))
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
        assertThrows<AssertionError> { ins.pullSByte() }
    }

    @Test
    fun pullAndPushBackTwice() {
        val ins = BufferSourceMemory(byteArrayOf(0, 1, 2, 3, 4, 5))
        assertEquals(0, ins.pullSByte())
        ins.pushBack()
        assertThrows<AssertionError> { ins.pushBack() }
    }

}