package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.mpack.pack.build
import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
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
        Assertions.assertNull(InMemoryUnpacker("c0".dehex()).unpackInt())
        Assertions.assertNull(InMemoryUnpacker("c0".dehex()).unpackLong())

        Assertions.assertArrayEquals("c0".dehex(), InMemoryPacker().packNil().build())
    }
}