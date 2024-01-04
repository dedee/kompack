package com.wunderbee.kompack.mpack.pack

import com.wunderbee.kompack.mpack.unpack.InMemoryUnpacker
import kotlin.test.Test
import kotlin.test.assertEquals

class MapTests {

    @Test
    fun `Encode and decode String Int Map`() {
        val m = mapOf(Pair("A", 1), Pair("B", 2), Pair("C", 3), Pair("D", 4))
        val encoded = InMemoryPacker(50).pack(m).build()
        val unpackedMap = InMemoryUnpacker(encoded).unpackMap() as Map<String, Int>
        println(unpackedMap)
        assertEquals(m, unpackedMap)
    }

    @Test
    fun `Encode and decode mixed map`() {
        val m = mutableMapOf<Any, Any>()
        m.put("A", 1)
        m.put(2, "B")
        val encoded = InMemoryPacker(50).pack(m).build()
        val unpackedMap = InMemoryUnpacker(encoded).unpackMap()
        println(unpackedMap)
        assertEquals(m, unpackedMap)
    }

    @Test
    fun `Map in map`() {
        val m = mapOf<Any, Map<Any, Any>>(
            Pair("persons", mapOf<Any, Any>(Pair("harry", 42), Pair("john", 25))),
            Pair("colors", mapOf<Any, Any>(Pair(10, "green"), Pair(20, "gold")))
        )
        val encoded = InMemoryPacker(50).pack(m).build()
        val unpackedMap = InMemoryUnpacker(encoded).unpackMap()
        println(unpackedMap)
        assertEquals(m, unpackedMap)
    }

    @Test
    fun `Array of maps`() {
        val a = arrayOf(
            mapOf<Any, Any>(Pair("harry", 42), Pair("john", 25)),
            mapOf<Any, Any>(Pair(10, "green"), Pair(20, "gold"))
        )
        val encoded = InMemoryPacker(50).pack(a).build()
        val unpackedArray = InMemoryUnpacker(encoded).unpackArray()

        assertEquals(2, unpackedArray!!.size)
        assertEquals(42, (unpackedArray[0] as Map<*, *>)["harry"])
        assertEquals("gold", (unpackedArray[1] as Map<*, *>)[20])
    }
}