package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class SerializerBooleanTests {

    private val packer = InMemoryPacker(10)

    @Test
    fun `Boolean NIL`() {
        Assertions.assertEquals("c0", packer.packNil().build().hex())
    }

    @Test
    fun `Boolean FALSE`() {
        // false:
        //+--------+
        //|  0xc2  |
        //+--------+
        //
        Assertions.assertEquals("c2", packer.pack(false).build().hex())
    }

    @Test
    fun `Boolean TRUE`() {
        //true:
        //+--------+
        //|  0xc3  |
        //+--------+
        Assertions.assertEquals("c3", packer.pack(true).build().hex())
    }
}