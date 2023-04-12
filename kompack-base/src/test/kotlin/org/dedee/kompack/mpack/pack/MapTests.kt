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
}