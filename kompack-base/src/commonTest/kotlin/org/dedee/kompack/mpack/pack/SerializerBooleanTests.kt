package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import kotlin.test.Test
import kotlin.test.assertEquals

class SerializerBooleanTests {

    private val packer = InMemoryPacker(10)

    @Test
    fun `Boolean NIL`() {
        assertEquals("c0", packer.packNil().build().hex())
    }

    @Test
    fun `Boolean FALSE`() {
        // false:
        //+--------+
        //|  0xc2  |
        //+--------+
        //
        assertEquals("c2", packer.pack(false).build().hex())
    }

    @Test
    fun `Boolean TRUE`() {
        //true:
        //+--------+
        //|  0xc3  |
        //+--------+
        assertEquals("c3", packer.pack(true).build().hex())
    }
}