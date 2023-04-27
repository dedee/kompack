package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.mpack.pack.build
import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Arrays {

    @Test
    fun `40 Array tests`() {
        // "40.array.yaml": [
        //    {
        //      "array": [],
        //      "msgpack": [
        //        "90",
        //        "dc-00-00",
        //        "dd-00-00-00-00"
        //      ]
        //    },

        assertEquals(0, InMemoryUnpacker("90".dehex()).unpackArray()!!.size)

        //    {
        //      "array": [ 1 ],
        //      "msgpack": [
        //        "91-01",
        //        "dc-00-01-01",
        //        "dd-00-00-00-01-01"
        //      ]
        //    },

        assertArrayEquals(arrayOf(1), InMemoryUnpacker("91-01".dehex()).unpackArray())
        assertArrayEquals(arrayOf(1), InMemoryUnpacker("dc-00-01-01".dehex()).unpackArray())
        assertArrayEquals(arrayOf(1), InMemoryUnpacker("dd-00-00-00-01-01".dehex()).unpackArray())


        //    {
        //      "array": [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 ],
        //      "msgpack": [
        //        "9f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f",
        //        "dc-00-0f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f",
        //        "dd-00-00-00-0f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f"
        //      ]
        //    },

        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
            InMemoryUnpacker("9f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f".dehex()).unpackArray()
        )
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
            InMemoryUnpacker("dc-00-0f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f".dehex()).unpackArray()
        )
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
            InMemoryUnpacker("dd-00-00-00-0f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f".dehex()).unpackArray()
        )

        //    {
        //      "array": [ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 ],
        //      "msgpack": [
        //        "dc-00-10-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f-10",
        //        "dd-00-00-00-10-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f-10"
        //      ]
        //    },

        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
            InMemoryUnpacker("dc-00-10-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f-10".dehex()).unpackArray()
        )
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
            InMemoryUnpacker("dd-00-00-00-10-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f-10".dehex()).unpackArray()
        )

        //    {
        //      "array": [
        //        "a"
        //      ],
        //      "msgpack": [
        //        "91-a1-61",
        //        "dc-00-01-a1-61",
        //        "dd-00-00-00-01-a1-61"
        //      ]
        //    }
        //  ],

        assertArrayEquals(arrayOf("a"), InMemoryUnpacker("91-a1-61".dehex()).unpackArray())
        assertArrayEquals(arrayOf("a"), InMemoryUnpacker("dc-00-01-a1-61".dehex()).unpackArray())
        assertArrayEquals(arrayOf("a"), InMemoryUnpacker("dd-00-00-00-01-a1-61".dehex()).unpackArray())

    }


    @Test
    fun `Large array test`() {
        val a = Array(70_000) { 10.toByte() }
        val flat = InMemoryPacker(70_100).pack(a).build()

        assertEquals("dd00011170", flat.copyOfRange(0, 5).hex())

        val b2 = InMemoryUnpacker(flat).unpackArray() as Array<Any>
        assertEquals(a.size, b2.size)
    }
}