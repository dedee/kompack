package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
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

        assertEquals(0, Unpacker(Source("90".dehex())).unpackArray()!!.size)

        //    {
        //      "array": [ 1 ],
        //      "msgpack": [
        //        "91-01",
        //        "dc-00-01-01",
        //        "dd-00-00-00-01-01"
        //      ]
        //    },

        assertArrayEquals(arrayOf(1), Unpacker(Source("91-01".dehex())).unpackArray())
        assertArrayEquals(arrayOf(1), Unpacker(Source("dc-00-01-01".dehex())).unpackArray())
        assertArrayEquals(arrayOf(1), Unpacker(Source("dd-00-00-00-01-01".dehex())).unpackArray())


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
            Unpacker(Source("9f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f".dehex())).unpackArray()
        )
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
            Unpacker(Source("dc-00-0f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f".dehex())).unpackArray()
        )
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15),
            Unpacker(Source("dd-00-00-00-0f-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f".dehex())).unpackArray()
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
            Unpacker(Source("dc-00-10-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f-10".dehex())).unpackArray()
        )
        assertArrayEquals(
            arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16),
            Unpacker(Source("dd-00-00-00-10-01-02-03-04-05-06-07-08-09-0a-0b-0c-0d-0e-0f-10".dehex())).unpackArray()
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

        assertArrayEquals(arrayOf("a"), Unpacker(Source("91-a1-61".dehex())).unpackArray())
        assertArrayEquals(arrayOf("a"), Unpacker(Source("dc-00-01-a1-61".dehex())).unpackArray())
        assertArrayEquals(arrayOf("a"), Unpacker(Source("dd-00-00-00-01-a1-61".dehex())).unpackArray())

    }

}