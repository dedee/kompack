package com.wunderbee.kompack.mpack.unpack

import com.wunderbee.kompack.mpack.util.BufferSourceMemory
import com.wunderbee.kompack.mpack.util.dehex
import kotlin.test.Test
import kotlin.test.assertEquals

class SourceTest {

    @Test
    fun pullUInt64() {
        assertEquals(ULong.MAX_VALUE, Source(BufferSourceMemory("ffffffffffffffff".dehex())).pullUInt64())
        assertEquals(1uL, Source(BufferSourceMemory("0000000000000001".dehex())).pullUInt64())
    }

    @Test
    fun pull128ULong() {
        assertEquals(128uL, Source(BufferSourceMemory("00-00-00-00--00-00-00-80".dehex())).pullUInt64())
    }

    @Test
    fun `Pull signed integers`() {
        val s = Source(BufferSourceMemory("ffffffffffffffffffffffffffffffff".dehex()))
        assertEquals(0xff, s.pullByte())
        assertEquals(-1, s.pullInt16())
        assertEquals(-1, s.pullInt32())
        assertEquals(-1, s.pullInt64())
    }

    @Test
    fun `Pull unsigned integers`() {
        val s = Source(BufferSourceMemory("ffffffffffffffffffffffffffffffff".dehex()))
        assertEquals(0xff, s.pullByte())
//        assertEquals(-1, s.pullUInt16())
//        assertEquals(-1, s.pullUInt32())
        assertEquals(ULong.MAX_VALUE, s.pullUInt64())
    }
}