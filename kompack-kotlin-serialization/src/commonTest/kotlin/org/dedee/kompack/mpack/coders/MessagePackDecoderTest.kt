package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertEquals

class MessagePackDecoderTest {

    @Serializable
    data class Foo(
        val b: Boolean
    )

    @Test
    fun `Decoder one boolean field`() {
        val f: Foo = MessagePackDecoder.decodeFromByteArray(byteArrayOf(0xc3.toByte()))
        assertEquals(Foo(true), f)
    }

}