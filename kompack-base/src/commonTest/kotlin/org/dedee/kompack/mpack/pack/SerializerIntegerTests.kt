package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializerIntegerTests {

    @Test
    fun `integer NIL`() {
        assertEquals("c0", InMemoryPacker(10).packNil().build().hex())
    }

    @Test
    fun `positive fixint stores 7-bit positive integer`() {
        // positive fixint stores 7-bit positive integer
        // +--------+
        // |0XXXXXXX|
        // +--------+
        assertEquals("00", InMemoryPacker(10).pack(0).build().hex())
        assertEquals("01", InMemoryPacker(10).pack(1).build().hex())
        assertEquals("7f", InMemoryPacker(10).pack(0x7f).build().hex())
    }

    @Test
    fun `negative fixint stores 5-bit negative integer`() {
        // negative fixint stores 5-bit negative integer
        // +--------+
        // |111YYYYY|
        // +--------+
        assertEquals("ff", InMemoryPacker(10).pack(-1).build().hex())
        assertEquals("e1", InMemoryPacker(10).pack(-31).build().hex())
        assertEquals("e0", InMemoryPacker(10).pack(-32).build().hex())
        assertEquals("d0df", InMemoryPacker(10).pack(-33).build().hex())
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
        assertEquals("ccff", InMemoryPacker(10).pack(0xff).build().hex())
        assertEquals("00", InMemoryPacker(10).pack(0).build().hex())
    }

    @Test
    fun `uint 16 stores a 16-bit big-endian unsigned integer`() {
        // uint 16 stores a 16-bit big-endian unsigned integer
        // +--------+--------+--------+
        // |  0xcd  |ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+
        assertEquals("cdffff", InMemoryPacker(10).pack(0xffff).build().hex())
        assertEquals("cd1fff", InMemoryPacker(10).pack(0x1fff).build().hex())
    }

    @Test
    fun `uint 32 stores a 32-bit big-endian unsigned integer`() {
        // uint 32 stores a 32-bit big-endian unsigned integer
        // +--------+--------+--------+--------+--------+
        // |  0xce  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+
        assertEquals("ce7fffffff", InMemoryPacker(10).pack(0x7fffffff).build().hex())
    }

    @Test
    fun `uint 64 stores a 64-bit big-endian unsigned integer`() {
        // uint 64 stores a 64-bit big-endian unsigned integer
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // |  0xcf  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        assertEquals("cfffffffffffffffff", InMemoryPacker(10).pack(0xffffffffffffffffuL).build().hex())
    }

    @Test
    fun `int 8 stores a 8-bit signed integer`() {
        // int 8 stores a 8-bit signed integer
        // +--------+--------+
        // |  0xd0  |ZZZZZZZZ|
        // +--------+--------+
        assertEquals("d081", InMemoryPacker(10).pack(-0x7f).build().hex())
        assertEquals("d0df", InMemoryPacker(10).pack(-33).build().hex())
        assertEquals("d1ff01", InMemoryPacker(10).pack(-0xff).build().hex())
    }

    @Test
    fun `int 16 stores a 16-bit big-endian signed integer`() {
        // int 16 stores a 16-bit big-endian signed integer
        // +--------+--------+--------+
        // |  0xd1  |ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+
        assertEquals("d18001", InMemoryPacker(10).pack(-0x7fff).build().hex())
        assertEquals("d1eff0", InMemoryPacker(10).pack(-0x1010).build().hex())
    }

    @Test
    fun `int 32 stores a 32-bit big-endian signed integer`() {
        // int 32 stores a 32-bit big-endian signed integer
        // +--------+--------+--------+--------+--------+
        // |  0xd2  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+
        assertEquals("d2ff800001", InMemoryPacker(10).pack(-0x7fffff).build().hex())
        assertEquals("d280000001", InMemoryPacker(10).pack(-0x7fffffff).build().hex())
        assertEquals("d2edcba988", InMemoryPacker(10).pack(-0x12345678).build().hex())
    }

    @Test
    fun `int 64 stores a 64-bit big-endian signed integer`() {
        // int 64 stores a 64-bit big-endian signed integer
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // |  0xd3  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        // +--------+--------+--------+--------+--------+--------+--------+--------+--------+
        assertEquals("d3ffffff8000000001", InMemoryPacker(10).pack(-0x7fffffffff).build().hex())
        assertEquals("d3ffff800000000001", InMemoryPacker(10).pack(-0x7fff_ffff_ffff).build().hex())
        assertEquals("d38000000000000001", InMemoryPacker(10).pack(-0x7fff_ffff_ffff_ffff).build().hex())
    }

}