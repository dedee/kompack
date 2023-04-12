package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.Sink
import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Nil {

    @Test
    fun `10 nil`() {
        //   "10.nil.yaml": [
        //    {
        //      "nil": null,
        //      "msgpack": [
        //        "c0"
        //      ]
        //    }
        //  ],
        Assertions.assertNull(Unpacker(Source("c0".dehex())).unpackInt())
        Assertions.assertNull(Unpacker(Source("c0".dehex())).unpackLong())

        Assertions.assertArrayEquals("c0".dehex(), Packer(Sink(ByteArray(5))).packNil().build())
    }
}