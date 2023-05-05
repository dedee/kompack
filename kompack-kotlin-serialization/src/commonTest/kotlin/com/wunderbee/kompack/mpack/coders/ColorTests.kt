package com.wunderbee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import com.wunderbee.kompack.mpack.util.hex
import kotlin.test.Test
import kotlin.test.assertEquals

class ColorTests {

    @Serializable
    data class Color(
        val name: String,
        val rgb: List<Int>,
    )

    @Serializable
    data class Spectrum(
        val color: List<Color>
    )

    @Test
    fun `List of colors`() {
        val s1 = Spectrum(
            listOf(
                Color("red", listOf(1, 2, 3)),
                Color("blue", listOf(4, 5, 6))
            )
        )
        val b = MessagePackEncoder.encodeToByteArray(s1)
        println(b.hex())
        val s2 = MessagePackDecoder.decodeFromByteArray<Spectrum>(b)
        println(s2)
        assertEquals(s1, s2)
    }

}