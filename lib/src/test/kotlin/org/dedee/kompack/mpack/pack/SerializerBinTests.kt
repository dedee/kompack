package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SerializerBinTests {

    private val dest = ByteArray(70_000)
    private val packer = Packer(Sink(dest))

    @Test
    fun `bin 8 stores a byte array whose length is upto 255 bytes`() {
        // bin 8 stores a byte array whose length is upto 255 bytes:
        // +--------+--------+========+
        // |  0xc4  |XXXXXXXX|  data  |
        // +--------+--------+========+
        assertEquals(
            "c409000102030405060708",
            packer.pack(byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8)).build().hex()
        )
    }

    @Test
    fun `bin 16 stores a byte array whose length is upto 65535 bytes`() {
        // bin 16 stores a byte array whose length is upto 65535 bytes:
        // +--------+--------+--------+========+
        // |  0xc5  |YYYYYYYY|YYYYYYYY|  data  |
        // +--------+--------+--------+========+
        assertEquals(
            "c50100", extractHex(
                packer.pack(buildBin(256, 0x10)).build(), 0, 3
            )
        )

        val b = packer.pack(buildBin(10_100, 0x10)).build()
        assertEquals("c527741010", extractHex(b, 0, 5))
    }

    @Test
    fun `bin 32 stores a byte array whose length is upto 4294967295 bytes`() {
        // bin 32 stores a byte array whose length is upto 4294967295 bytes:
        // +--------+--------+--------+--------+--------+========+
        // |  0xc6  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|  data  |
        // +--------+--------+--------+--------+--------+========+
        val b = packer.pack(buildBin(68_123, 0x10)).build()
        assertEquals("c600010a1b", extractHex(b, 0, 5))
    }

    private fun buildBin(length: Int, element: Byte): ByteArray {
        val b = ByteArray(length)
        b.fill(element)
        return b
    }

    private fun extractHex(b: ByteArray, offset: Int, len: Int): String {
        return b.copyOfRange(offset, offset + len).hex()
    }

    @Test
    fun testExtractHex() {
        assertEquals("affe", extractHex(byteArrayOf(0, 1, 0xaf.toByte(), 0xfe.toByte(), 3, 4), 2, 2))
    }
}