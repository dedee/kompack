package com.wunderbee.kompack.mpack.testsuite

import com.wunderbee.kompack.mpack.pack.InMemoryPacker
import com.wunderbee.kompack.mpack.pack.build
import com.wunderbee.kompack.mpack.unpack.InMemoryUnpacker
import com.wunderbee.kompack.mpack.util.dehex
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