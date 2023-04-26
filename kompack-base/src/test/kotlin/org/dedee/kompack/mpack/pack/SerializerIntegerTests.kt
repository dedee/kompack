package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SerializerIntegerTests {

    private val dest = ByteArray(10)
    private val packer = Packer(SinkInMemory(dest))

    @Test
    fun `integer NIL`() {
        assertEquals("c0", packer.packNil().build().hex())
    }
    @Test
    fun `positive fixint stores 7-bit positive integer`() {
        // positive fixint stores 7-bit positive integer
        // +--------+
        // |0XXXXXXX|
        // +--------+
        assertEquals("00", packer.pack(0).build().hex())
        assertEquals("01", packer.pack(1).build().hex())
        assertEquals("7f", packer.pack(0x7f).build().hex())
    }

    @Test
    fun `negative fixint stores 5-bit negative integer`() {
        // negative fixint stores 5-bit negative integer
        // +--------+
        // |111YYYYY|
        // +--------+
        assertEquals("ff", packer.pack(-1).build().hex())
        assertEquals("e1", packer.pack(-31).build().hex())
        assertEquals("e0", packer.pack(-32).build().hex())
        assertEquals("d0df", packer.pack(-33).build().hex())
    }

    @Test
    fun `uint 8 stores a 8-bit unsigned integer`() {
        // 0XXX XXXX is 8-bit unsigned integer
        // 111Y YYYY is 8-bit signed integer
        //
        // uint 8 stores a 8-bit unsigned integer
        // +--------+--------+
        // |  0xcc  |ZZZZZZZZ|
        // +--------+--------+
        assertEquals("ccff", packer.pack(0xff).build().hex())
        assertEquals("00", packer.pack(0).build().hex())
    }

    @Test
    fun `uint 16 stores a 16-bit big-endian unsigned integer`() {
        // uint 16 stores a 16-bit big-endian unsigned integer
        // +--------+--------+--------+
        // |  0xcd  |ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+
        assertEquals("cdffff", packer.pack(0xffff).build().hex())
        assertEquals("cd1fff", packer.pack(0x1fff).build().hex())
    }

    @Test
    fun `uint 32 stores a 32-bit big-endian unsigned integer`() {
        // uint 32 stores a 32-bit big-endian unsigned integer
        // +--------+--------+--------+--------+--------+
        // |  0xce  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+
        assertEquals("ce7fffffff", packer.pack(0x7fffffff).build().hex())
    }

    @Test
    fun `uint 64 stores a 64-bit big-endian unsigned integer`() {
        // uint 64 stores a 64-bit big-endian unsigned integer
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // |  0xcf  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        assertEquals("cfffffffffffffffff", packer.pack(0xffffffffffffffffuL).build().hex())
    }

    @Test
    fun `int 8 stores a 8-bit signed integer`() {
        // int 8 stores a 8-bit signed integer
        // +--------+--------+
        // |  0xd0  |ZZZZZZZZ|
        // +--------+--------+
        assertEquals("d081", packer.pack(-0x7f).build().hex())
        assertEquals("d0df", packer.pack(-33).build().hex())
        assertEquals("d1ff01", packer.pack(-0xff).build().hex())
    }

    @Test
    fun `int 16 stores a 16-bit big-endian signed integer`() {
        // int 16 stores a 16-bit big-endian signed integer
        // +--------+--------+--------+
        // |  0xd1  |ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+
        assertEquals("d18001", packer.pack(-0x7fff).build().hex())
        assertEquals("d1eff0", packer.pack(-0x1010).build().hex())
    }

    @Test
    fun `int 32 stores a 32-bit big-endian signed integer`() {
        // int 32 stores a 32-bit big-endian signed integer
        // +--------+--------+--------+--------+--------+
        // |  0xd2  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+
        assertEquals("d2ff800001", packer.pack(-0x7fffff).build().hex())
        assertEquals("d280000001", packer.pack(-0x7fffffff).build().hex())
        assertEquals("d2edcba988", packer.pack(-0x12345678).build().hex())
    }

    @Test
    fun `int 64 stores a 64-bit big-endian signed integer`() {
        // int 64 stores a 64-bit big-endian signed integer
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // |  0xd3  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        assertEquals("d3ffffff8000000001", packer.pack(-0x7fffffffff).build().hex())
        assertEquals("d3ffff800000000001", packer.pack(-0x7fff_ffff_ffff).build().hex())
        assertEquals("d38000000000000001", packer.pack(-0x7fff_ffff_ffff_ffff).build().hex())
    }

}