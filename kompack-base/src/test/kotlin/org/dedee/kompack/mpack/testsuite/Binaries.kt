package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.SinkInMemory
import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Binaries {
    @Test
    fun `12 binary`() {

        assertNull(Unpacker(Source("c0".dehex())).unpackBinary())
        assertThrows(Exception::class.java) { Unpacker(Source("01".dehex())).unpackBinary() }
        assertArrayEquals("c400".dehex(), Packer(SinkInMemory(ByteArray(5))).pack(ByteArray(0)).build())


        //  "12.binary.yaml": [
        //    {
        //      "binary": "",
        //      "msgpack": [
        //        "c4-00",
        //        "c5-00-00",
        //        "c6-00-00-00-00"
        //      ]
        //    },

        assertArrayEquals(ByteArray(0), Unpacker(Source("c400".dehex())).unpackBinary()!!)
        assertArrayEquals(ByteArray(0), Unpacker(Source("c50000".dehex())).unpackBinary()!!)
        assertArrayEquals(ByteArray(0), Unpacker(Source("c600000000".dehex())).unpackBinary()!!)

        //    {
        //      "binary": "01",
        //      "msgpack": [
        //        "c4-01-01",
        //        "c5-00-01-01",
        //        "c6-00-00-00-01-01"
        //      ]
        //    },

        assertArrayEquals(byteArrayOf(0x01), Unpacker(Source("c4-01-01".dehex())).unpackBinary()!!)
        assertArrayEquals(byteArrayOf(0x01), Unpacker(Source("c5-00-01-01".dehex())).unpackBinary()!!)
        assertArrayEquals(byteArrayOf(0x01), Unpacker(Source("c6-00-00-00-01-01".dehex())).unpackBinary()!!)

        //    {
        //      "binary": "00-ff",
        //      "msgpack": [
        //        "c4-02-00-ff",
        //        "c5-00-02-00-ff",
        //        "c6-00-00-00-02-00-ff"
        //      ]
        //    }
        //  ],

        assertArrayEquals(byteArrayOf(0, 0xff.toByte()), Unpacker(Source("c4-02-00-ff".dehex())).unpackBinary()!!)
        assertArrayEquals(byteArrayOf(0, 0xff.toByte()), Unpacker(Source("c5-00-02-00-ff".dehex())).unpackBinary()!!)
        assertArrayEquals(byteArrayOf(0, 0xff.toByte()), Unpacker(Source("c6-00-00-00-02-00-ff".dehex())).unpackBinary()!!)


    }
}