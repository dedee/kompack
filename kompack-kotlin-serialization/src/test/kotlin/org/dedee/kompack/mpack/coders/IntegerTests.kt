package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class IntegerTests {

    @Serializable
    data class IntKeeper(
        val i: Int,
        val ni: Int?,
    )

    @Serializable
    data class LongKeeper(
        val l: Long,
        val nl: Long?,
    )

    @Serializable
    data class ByteKeeper(
        val b: Byte,
        val nb: Byte?,
    )

    @Serializable
    data class ShortKeeper(
        val s: Short,
        val ns: Short?,
    )

    @Test
    fun `Test Int`() {
        val s1 = IntKeeper(1, null)
        val s2 = MessagePackDecoder.decodeFromByteArray<IntKeeper>(MessagePackEncoder.encodeToByteArray(s1))
        println(s2)
        assertEquals(s1, s2)
    }

    @Test
    fun `Test Int Long`() {
        val s1 = IntKeeper(72123, null)
        val s2 = MessagePackDecoder.decodeFromByteArray<IntKeeper>(MessagePackEncoder.encodeToByteArray(s1))
        println(s2)
        assertEquals(s1, s2)
    }

    @Test
    fun `Test Long`() {
        val s1 = LongKeeper(72987, null)
        val b = MessagePackEncoder.encodeToByteArray(s1)
        println(b.hex())
        val s2 = MessagePackDecoder.decodeFromByteArray<LongKeeper>(b)
        println(s2)
        assertEquals(s1, s2)
    }

    @Test
    fun `Test Byte`() {
        val s1 = ByteKeeper(1, null)
        val b = MessagePackEncoder.encodeToByteArray(s1)
        println(b.hex())
        val s2 = MessagePackDecoder.decodeFromByteArray<ByteKeeper>(b)
        println(s2)
        assertEquals(s1, s2)
    }

    @Test
    fun `Test Short`() {
        val s1 = ShortKeeper(1, null)
        val b = MessagePackEncoder.encodeToByteArray(s1)
        println(b.hex())
        val s2 = MessagePackDecoder.decodeFromByteArray<ShortKeeper>(b)
        println(s2)
        assertEquals(s1, s2)
    }
}