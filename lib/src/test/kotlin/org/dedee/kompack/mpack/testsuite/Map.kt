package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class Map {

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

        Assertions.assertEquals(0, Unpacker(Source("80".dehex())).unpackMap()!!.size)
        Assertions.assertEquals(0, Unpacker(Source("de-00-00".dehex())).unpackMap()!!.size)
        Assertions.assertEquals(0, Unpacker(Source("df-00-00-00-00".dehex())).unpackMap()!!.size)

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

        Assertions.assertEquals(mapOf(Pair("a", 1)), Unpacker(Source("81-a1-61-01".dehex())).unpackMap())
        Assertions.assertEquals(mapOf(Pair("a", 1)), Unpacker(Source("de-00-01-a1-61-01".dehex())).unpackMap())
        Assertions.assertEquals(mapOf(Pair("a", 1)), Unpacker(Source("df-00-00-00-01-a1-61-01".dehex())).unpackMap())

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

        Assertions.assertEquals(mapOf(Pair("a", "A")), Unpacker(Source("81-a1-61-a1-41".dehex())).unpackMap())
        Assertions.assertEquals(mapOf(Pair("a", "A")), Unpacker(Source("de-00-01-a1-61-a1-41".dehex())).unpackMap())
        Assertions.assertEquals(mapOf(Pair("a", "A")), Unpacker(Source("df-00-00-00-01-a1-61-a1-41".dehex())).unpackMap())
    }
}