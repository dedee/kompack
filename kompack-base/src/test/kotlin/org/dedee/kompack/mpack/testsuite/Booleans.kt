package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.mpack.pack.build
import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Booleans {

    @Test
    fun `11 bool`() {
        //   "11.bool.yaml": [
        //    {
        //      "bool": false,
        //      "msgpack": [
        //        "c2"
        //      ]
        //    },

        assertFalse(InMemoryUnpacker("c2".dehex()).unpackBoolean()!!)

        //    {
        //      "bool": true,
        //      "msgpack": [
        //        "c3"
        //      ]
        //    }
        //  ],

        assertTrue(InMemoryUnpacker("c3".dehex()).unpackBoolean()!!)
    }

    @Test
    fun otherStuff() {
        assertNull(InMemoryUnpacker("c0".dehex()).unpackBoolean())
        assertThrows(Exception::class.java) { InMemoryUnpacker("01".dehex()).unpackBoolean() }
        assertArrayEquals("c2".dehex(), InMemoryPacker().pack(false).build())
        assertArrayEquals("c3".dehex(), InMemoryPacker().pack(true).build())
    }


}