package com.wunderbee.kompack.mpack.testsuite

import com.wunderbee.kompack.mpack.pack.InMemoryPacker
import com.wunderbee.kompack.mpack.pack.build
import com.wunderbee.kompack.mpack.unpack.InMemoryUnpacker
import com.wunderbee.kompack.mpack.util.dehex
import kotlin.test.*

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
        assertFailsWith<Exception> { InMemoryUnpacker("01".dehex()).unpackBoolean() }
        assertContentEquals("c2".dehex(), InMemoryPacker().pack(false).build())
        assertContentEquals("c3".dehex(), InMemoryPacker().pack(true).build())
    }


}