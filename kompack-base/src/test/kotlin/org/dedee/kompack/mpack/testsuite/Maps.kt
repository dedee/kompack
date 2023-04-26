package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Maps {

    @Test
    fun `41 map`() {
        //  "41.map.yaml": [
        //    {
        //      "map": {},
        //      "msgpack": [
        //        "80",
        //        "de-00-00",
        //        "df-00-00-00-00"
        //      ]
        //    },

        Assertions.assertEquals(0, InMemoryUnpacker("80".dehex()).unpackMap()!!.size)
        Assertions.assertEquals(0, InMemoryUnpacker("de-00-00".dehex()).unpackMap()!!.size)
        Assertions.assertEquals(0, InMemoryUnpacker("df-00-00-00-00".dehex()).unpackMap()!!.size)

        //    {
        //      "map": {
        //        "a": 1
        //      },
        //      "msgpack": [
        //        "81-a1-61-01",
        //        "de-00-01-a1-61-01",
        //        "df-00-00-00-01-a1-61-01"
        //      ]
        //    },

        Assertions.assertEquals(mapOf(Pair("a", 1)), InMemoryUnpacker("81-a1-61-01".dehex()).unpackMap())
        Assertions.assertEquals(mapOf(Pair("a", 1)), InMemoryUnpacker("de-00-01-a1-61-01".dehex()).unpackMap())
        Assertions.assertEquals(mapOf(Pair("a", 1)), InMemoryUnpacker("df-00-00-00-01-a1-61-01".dehex()).unpackMap())

        //    {
        //      "map": {
        //        "a": "A"
        //      },
        //      "msgpack": [
        //        "81-a1-61-a1-41",
        //        "de-00-01-a1-61-a1-41",
        //        "df-00-00-00-01-a1-61-a1-41"
        //      ]
        //    }
        //  ],

        Assertions.assertEquals(mapOf(Pair("a", "A")), InMemoryUnpacker("81-a1-61-a1-41".dehex()).unpackMap())
        Assertions.assertEquals(mapOf(Pair("a", "A")), InMemoryUnpacker("de-00-01-a1-61-a1-41".dehex()).unpackMap())
        Assertions.assertEquals(
            mapOf(Pair("a", "A")),
            InMemoryUnpacker("df-00-00-00-01-a1-61-a1-41".dehex()).unpackMap()
        )

        val m: Map<String, String>? = InMemoryUnpacker("81-a1-61-a1-41".dehex()).unpakk()
        println(m)
        Assertions.assertEquals(
            mapOf(Pair("a", "A")),
            m
        )
    }
}