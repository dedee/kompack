package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class SinkTest {

    private val dest = ByteArray(10)
    private val s = Sink(dest)

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

    @Test
    fun `Append too many bytes`() {
        assertThrows<ArrayIndexOutOfBoundsException> {
            for (i in 0..dest.size + 1) {
                s.addByte(42)
            }
        }
    }

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