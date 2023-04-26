package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.ByteArrayOutputStream

class SinkStreamTest {

    val out = ByteArrayOutputStream()

    @Test
    fun `Byte 00`() {
        SinkStream(out).addByte(0x00)
        assertEquals("00", out.toByteArray().hex())
    }

    @Test
    fun `Byte ff`() {
        SinkStream(out).addByte(0xff)
        assertEquals("ff", out.toByteArray().hex())
    }

    @Test
    fun `Byte fff`() {
        SinkStream(out).addInt16(0xfff)
        assertEquals("0fff", out.toByteArray().hex())
    }

    @Test
    fun `Int ffff`() {
        SinkStream(out).addInt32(0xffff)
        assertEquals("0000ffff", out.toByteArray().hex())
    }

    // TODO more to test
}