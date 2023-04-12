package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class MessagePackDecoderTest {

    @Serializable
    data class Foo(
        val b: Boolean
    )

    @Test
    fun `Decoder one boolean field`() {
        val f: Foo = MessagePackDecoder.decodeFromByteArray(byteArrayOf(0xc3.toByte()))
        Assertions.assertEquals(Foo(true), f)
    }

}