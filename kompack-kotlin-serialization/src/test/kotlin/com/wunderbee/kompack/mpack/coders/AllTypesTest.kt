package com.wunderbee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class AllTypesTest {

    @Serializable
    data class NativeTypes(
        val i: Int,
        val l: Long,
        val ul: ULong,
        val ch: Char,
        val s: String,
        val d: Double,
        val f: Float,
        val short: Short,
    )

    @Test
    fun `Test with all types`() {

        val n1 = NativeTypes(10, 10L, 10uL, '1', "10", 10.0, 10.0f, 10.toShort())
        val b = MessagePackEncoder.encodeToByteArray(n1)
        val n2 = MessagePackDecoder.decodeFromByteArray<NativeTypes>(b)

        assertEquals(n1, n2)
    }
}