package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.Sink
import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Boolean {

    @Test
    fun `11 bool`() {
        //   "11.bool.yaml": [
        //    {
        //      "bool": false,
        //      "msgpack": [
        //        "c2"
        //      ]
        //    },

        assertFalse(Unpacker(Source("c2".dehex())).unpackBoolean()!!)

        //    {
        //      "bool": true,
        //      "msgpack": [
        //        "c3"
        //      ]
        //    }
        //  ],

        assertTrue(Unpacker(Source("c3".dehex())).unpackBoolean()!!)
    }

    @Test
    fun otherStuff() {
        assertNull(Unpacker(Source("c0".dehex())).unpackBoolean())
        assertThrows(Exception::class.java) { Unpacker(Source("01".dehex())).unpackBoolean() }
        assertArrayEquals("c2".dehex(), Packer(Sink(ByteArray(5))).pack(false).build())
        assertArrayEquals("c3".dehex(), Packer(Sink(ByteArray(5))).pack(true).build())
    }


}