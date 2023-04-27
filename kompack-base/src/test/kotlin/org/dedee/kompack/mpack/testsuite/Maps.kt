package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.mpack.pack.build
import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.*
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

        assertEquals(0, InMemoryUnpacker("80".dehex()).unpackMap()!!.size)
        assertEquals(0, InMemoryUnpacker("de-00-00".dehex()).unpackMap()!!.size)
        assertEquals(0, InMemoryUnpacker("df-00-00-00-00".dehex()).unpackMap()!!.size)

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

        assertEquals(mapOf(Pair("a", 1)), InMemoryUnpacker("81-a1-61-01".dehex()).unpackMap())
        assertEquals(mapOf(Pair("a", 1)), InMemoryUnpacker("de-00-01-a1-61-01".dehex()).unpackMap())
        assertEquals(mapOf(Pair("a", 1)), InMemoryUnpacker("df-00-00-00-01-a1-61-01".dehex()).unpackMap())

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

        assertEquals(mapOf(Pair("a", "A")), InMemoryUnpacker("81-a1-61-a1-41".dehex()).unpackMap())
        assertEquals(mapOf(Pair("a", "A")), InMemoryUnpacker("de-00-01-a1-61-a1-41".dehex()).unpackMap())
        assertEquals(
            mapOf(Pair("a", "A")),
            InMemoryUnpacker("df-00-00-00-01-a1-61-a1-41".dehex()).unpackMap()
        )

        val m: Map<String, String>? = InMemoryUnpacker("81-a1-61-a1-41".dehex()).unpakk()
        println(m)
        assertEquals(
            mapOf(Pair("a", "A")),
            m
        )
    }


    @Test
    fun `Midsize map test`() {
        val m = mutableMapOf<Int, Int>()
        repeat(20_000) {
            m[it] = 10
        }

        val flat = InMemoryPacker(80_000).pack(m).build()

        assertEquals(79619, flat.size)
        assertEquals("de4e20000a", flat.copyOfRange(0, 5).hex())

        val m2 = InMemoryUnpacker(flat).unpackMap() as Map<*, *>
        assertEquals(m.size, m2.size)
    }
    @Test
    fun `Large map test`() {
        val m = mutableMapOf<Int, Int>()
        repeat(70_000) {
            m[it] = 10
        }

        val flat = InMemoryPacker(290_000).pack(m).build()

        assertEquals(288549, flat.size)
        assertEquals("df00011170", flat.copyOfRange(0, 5).hex())

        val m2 = InMemoryUnpacker(flat).unpackMap() as Map<*, *>
        assertEquals(m.size, m2.size)
    }

}