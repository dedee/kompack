package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SerializerStringTest {

    @Test
    fun `fixstr stores a byte array whose length is upto 31 bytes`() {
        // fixstr stores a byte array whose length is upto 31 bytes:
        //+--------+========+
        //|101XXXXX|  data  |
        //+--------+========+
        assertEquals("a3414243", InMemoryPacker(10).pack("ABC").build().hex())
        assertEquals("a0", InMemoryPacker(10).pack("").build().hex())
        assertEquals("a131", InMemoryPacker(10).pack("1").build().hex())
        assertEquals(
            "bf30313233343536373839303132333435363738393031323334353637383930",
            InMemoryPacker(50).pack("0123456789012345678901234567890").build().hex()
        )
    }

    @Test
    fun `str 8 stores a byte array whose length is upto 255 bytes`() {
        //str 8 stores a byte array whose length is upto 255 bytes:
        //+--------+--------+========+
        //|  0xd9  |YYYYYYYY|  data  |
        //+--------+--------+========+
        assertEquals(
            "d9203031323334353637383930313233343536373839303132333435363738393031",
            InMemoryPacker(50).pack("01234567890123456789012345678901").build().hex()
        )
        val b = InMemoryPacker(300).pack(buildString('A', 255)).build()
        assertEquals("d9ff", extractHex(b, 0, 2))
        for (i in 2 until 2 + 255) {
            assertEquals(65, b[i])
        }
    }

    @Test
    fun `str 16 stores a byte array whose length is upto 65535 bytes`() {
        //str 16 stores a byte array whose length is upto 65535 bytes:
        //+--------+--------+--------+========+
        //|  0xda  |ZZZZZZZZ|ZZZZZZZZ|  data  |
        //+--------+--------+--------+========+
        val b = InMemoryPacker(70_000).pack(buildString('A', 65535)).build()
        assertEquals("daffff", extractHex(b, 0, 3))
        for (i in 3 until 3 + 65535) {
            assertEquals(65, b[i])
        }
    }

    @Test
    fun `str 32 stores a byte array whose length is upto 4294967295 bytes`() {
        //str 32 stores a byte array whose length is upto 4294967295 bytes:
        //+--------+--------+--------+--------+--------+========+
        //|  0xdb  |AAAAAAAA|AAAAAAAA|AAAAAAAA|AAAAAAAA|  data  |
        //+--------+--------+--------+--------+--------+========+
        val b = InMemoryPacker(70_000).pack(buildString('A', 69_123)).build()
        assertEquals("db00010e03", extractHex(b, 0, 5))
        for (i in 5 until 5 + 69_123) {
            assertEquals(65, b[i])
        }
    }

    private fun extractHex(b: ByteArray, offset: Int, len: Int): Any {
        return b.copyOfRange(offset, offset + len).hex()
    }

    @Test
    fun testExtractHex() {
        assertEquals("affe", extractHex(byteArrayOf(0, 1, 0xaf.toByte(), 0xfe.toByte(), 3, 4), 2, 2))
    }

    @Test
    fun testBuildString() {
        assertEquals("", buildString('1', 0))
        assertEquals("1", buildString('1', 1))
    }

    private fun buildString(ch: Char, len: Int): String {
        val sb = StringBuilder()
        for (i in 1..len) {
            sb.append(ch)
        }
        return sb.toString()
    }

}