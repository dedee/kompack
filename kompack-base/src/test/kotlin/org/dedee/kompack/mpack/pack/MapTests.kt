package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MapTests {

    @Test
    fun `Encode and decode String Int Map`() {
        val p = Packer(Sink(ByteArray(100)))
        val m = mapOf(Pair("A", 1), Pair("B", 2), Pair("C", 3), Pair("D", 4))
        p.pack(m)
        val encoded = p.build()

        val unpackedMap = Unpacker(Source(encoded)).unpackMap()
        println(unpackedMap)

        assertEquals(m, unpackedMap)
    }

    @Test
    fun `Encode and decode mixed map`() {
        val p = Packer(Sink(ByteArray(100)))

        val m = mutableMapOf<Any, Any>()
        m.put("A", 1)
        m.put(2, "B")
        p.pack(m)
        val encoded = p.build()

        val unpackedMap = Unpacker(Source(encoded)).unpackMap()
        println(unpackedMap)

        assertEquals(m, unpackedMap)
    }

    @Test
    fun `Map in map`() {
        val m = mapOf<Any, Map<Any, Any>>(
            Pair("persons", mapOf<Any, Any>(Pair("harry", 42), Pair("john", 25))),
            Pair("colors", mapOf<Any, Any>(Pair(10, "green"), Pair(20, "gold")))
        )
        val encoded = Packer(Sink(ByteArray(100))).pack(m).build()
        val unpackedMap = Unpacker(Source(encoded)).unpackMap()
        println(unpackedMap)
        assertEquals(m, unpackedMap)
    }

    @Test
    fun `Array of maps`() {
        val a = arrayOf(
            mapOf<Any, Any>(Pair("harry", 42), Pair("john", 25)),
            mapOf<Any, Any>(Pair(10, "green"), Pair(20, "gold"))
        )
        val encoded = Packer(Sink(ByteArray(100))).pack(a).build()
        val unpackedArray = Unpacker(Source(encoded)).unpackArray()

        assertEquals(2, unpackedArray!!.size)
        assertEquals(42, (unpackedArray[0] as Map<*, *>)["harry"])
        assertEquals("gold", (unpackedArray[1] as Map<*, *>)[20])
    }
}