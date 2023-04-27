package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.mpack.pack.build
import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class Binaries {
    @Test
    fun `12 binary`() {

        assertNull(InMemoryUnpacker("c0".dehex()).unpackBinary())
        assertThrows(Exception::class.java) { InMemoryUnpacker("01".dehex()).unpackBinary() }


        //  "12.binary.yaml": [
        //    {
        //      "binary": "",
        //      "msgpack": [
        //        "c4-00",
        //        "c5-00-00",
        //        "c6-00-00-00-00"
        //      ]
        //    },

        assertArrayEquals(ByteArray(0), InMemoryUnpacker("c400".dehex()).unpackBinary()!!)
        assertArrayEquals(ByteArray(0), InMemoryUnpacker("c50000".dehex()).unpackBinary()!!)
        assertArrayEquals(ByteArray(0), InMemoryUnpacker("c600000000".dehex()).unpackBinary()!!)

        //    {
        //      "binary": "01",
        //      "msgpack": [
        //        "c4-01-01",
        //        "c5-00-01-01",
        //        "c6-00-00-00-01-01"
        //      ]
        //    },

        assertArrayEquals(byteArrayOf(0x01), InMemoryUnpacker("c4-01-01".dehex()).unpackBinary()!!)
        assertArrayEquals(byteArrayOf(0x01), InMemoryUnpacker("c5-00-01-01".dehex()).unpackBinary()!!)
        assertArrayEquals(byteArrayOf(0x01), InMemoryUnpacker("c6-00-00-00-01-01".dehex()).unpackBinary()!!)

        //    {
        //      "binary": "00-ff",
        //      "msgpack": [
        //        "c4-02-00-ff",
        //        "c5-00-02-00-ff",
        //        "c6-00-00-00-02-00-ff"
        //      ]
        //    }
        //  ],

        assertArrayEquals(byteArrayOf(0, 0xff.toByte()), InMemoryUnpacker("c4-02-00-ff".dehex()).unpackBinary()!!)
        assertArrayEquals(byteArrayOf(0, 0xff.toByte()), InMemoryUnpacker("c5-00-02-00-ff".dehex()).unpackBinary()!!)
        assertArrayEquals(
            byteArrayOf(0, 0xff.toByte()),
            InMemoryUnpacker("c6-00-00-00-02-00-ff".dehex()).unpackBinary()!!
        )


    }

    @Test
    fun `Large bin test`() {
        val b = ByteArray(20_000_000)
        val flat = InMemoryPacker(20_000_100).pack(b).build()
        val b2 = InMemoryUnpacker(flat).unpackBinary()
        assertContentEquals(b, b2)
    }
}