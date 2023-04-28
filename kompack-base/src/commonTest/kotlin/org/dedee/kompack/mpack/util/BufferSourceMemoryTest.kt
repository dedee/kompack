package org.dedee.kompack.mpack.util

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

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
        assertFailsWith<Throwable> { ins.pullSByte() }
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
        assertFailsWith<Throwable> { ins.pullSByte() }
    }

    @Test
    fun pullAndPushBackTwice() {
        val ins = BufferSourceMemory(byteArrayOf(0, 1, 2, 3, 4, 5))
        assertEquals(0, ins.pullSByte())
        ins.pushBack()
        assertFailsWith<Throwable> { ins.pushBack() }
    }

}