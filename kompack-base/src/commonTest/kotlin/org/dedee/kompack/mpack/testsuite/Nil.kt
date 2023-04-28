package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.mpack.pack.build
import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertNull

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
        assertNull(InMemoryUnpacker("c0".dehex()).unpackInt())
        assertNull(InMemoryUnpacker("c0".dehex()).unpackLong())

        assertContentEquals("c0".dehex(), InMemoryPacker().packNil().build())
    }
}