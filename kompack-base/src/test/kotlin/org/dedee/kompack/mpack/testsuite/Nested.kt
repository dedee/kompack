package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.Array
import kotlin.collections.Map

class Nested {

    @Test
    fun `42 Nested`() {
        //  "42.nested.yaml": [
        //    {
        //      "array": [
        //        []
        //      ],
        //      "msgpack": [
        //        "91-90",
        //        "dc-00-01-dc-00-00",
        //        "dd-00-00-00-01-dd-00-00-00-00"
        //      ]
        //    },

        assertEquals(0, ((Unpacker(Source("91-90".dehex())).unpack() as Array<*>)[0] as Array<*>).size)
        assertEquals(0, ((Unpacker(Source("dc-00-01-dc-00-00".dehex())).unpack() as Array<*>)[0] as Array<*>).size)
        assertEquals(
            0,
            ((Unpacker(Source("dd-00-00-00-01-dd-00-00-00-00".dehex())).unpack() as Array<*>)[0] as Array<*>).size
        )

        //    {
        //      "array": [
        //        {}
        //      ],
        //      "msgpack": [
        //        "91-80",
        //        "dc-00-01-80",
        //        "dd-00-00-00-01-80"
        //      ]
        //    },

        assertEquals(0, ((Unpacker(Source("91-80".dehex())).unpack() as Array<*>)[0] as Map<*, *>).size)
        assertEquals(0, ((Unpacker(Source("dc-00-01-80".dehex())).unpack() as Array<*>)[0] as Map<*, *>).size)
        assertEquals(0, ((Unpacker(Source("dd-00-00-00-01-80".dehex())).unpack() as Array<*>)[0] as Map<*, *>).size)

        //    {
        //      "map": {
        //        "a": {}
        //      },
        //      "msgpack": [
        //        "81-a1-61-80",
        //        "de-00-01-a1-61-de-00-00",
        //        "df-00-00-00-01-a1-61-df-00-00-00-00"
        //      ]
        //    },

        assertEquals(0, ((Unpacker(Source("81-a1-61-80".dehex())).unpack() as Map<*, *>)["a"] as Map<*, *>).size)
        assertEquals(0, ((Unpacker(Source("de-00-01-a1-61-de-00-00".dehex())).unpack() as Map<*, *>)["a"] as Map<*, *>).size)
        assertEquals(0, ((Unpacker(Source("df-00-00-00-01-a1-61-df-00-00-00-00".dehex())).unpack() as Map<*, *>)["a"] as Map<*, *>).size)

        //    {
        //      "map": {
        //        "a": []
        //      },
        //      "msgpack": [
        //        "81-a1-61-90",
        //        "de-00-01-a1-61-90",
        //        "df-00-00-00-01-a1-61-90"
        //      ]
        //    }
        //  ],

        assertEquals(0, ((Unpacker(Source("81-a1-61-90".dehex())).unpack() as Map<*, *>)["a"] as Array<*>).size)
        assertEquals(0, ((Unpacker(Source("de-00-01-a1-61-90".dehex())).unpack() as Map<*, *>)["a"] as Array<*>).size)
        assertEquals(0, ((Unpacker(Source("df-00-00-00-01-a1-61-90".dehex())).unpack() as Map<*, *>)["a"] as Array<*>).size)

    }

}