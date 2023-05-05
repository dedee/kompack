package com.wunderbee.kompack.mpack.pack

import com.wunderbee.kompack.mpack.util.hex
import kotlin.test.Test
import kotlin.test.assertEquals

class SinkTest {

    private val dest = ByteArray(10)
    private val s = SinkInMemory(dest)

    @Test
    fun `Nothing added Empty`() {
        assertEquals("", s.build().hex())
    }

    @Test
    fun `Append one byte`() {
        s.addByte(0)
        assertEquals("00", s.build().hex())
    }

    @Test
    fun `Append two bytes`() {
        s.addByte(1)
        s.addByte(2)
        assertEquals("0102", s.build().hex())
    }

//    @Test
//    fun `Append too many bytes`() {
//        assertFailsWith<ArrayIndexOutOfBoundsException> {
//            for (i in 0..dest.size + 1) {
//                s.addByte(42)
//            }
//        }
//    }

    @Test
    fun `build resets the length`() {
        s.addByte(1)
        s.addByte(2)
        assertEquals("0102", s.build().hex())
        // second call returns empty
        assertEquals("", s.build().hex())
    }

    @Test
    fun `toHexString resets the length`() {
        s.addByte(1)
        s.addByte(2)
        assertEquals("0102", s.toHexString())
        // second call returns empty
        assertEquals("", s.toHexString())
    }
}