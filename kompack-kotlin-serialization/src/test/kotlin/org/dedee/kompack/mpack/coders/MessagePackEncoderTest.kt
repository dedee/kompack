package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class MessagePackEncoderTest {

    @Serializable
    data class Foo(
        val b: Boolean
    )

    @Test
    fun `Encode one boolean field`() {
        assertArrayEquals(byteArrayOf(0xc3.toByte()), MessagePackEncoder.encodeToByteArray(Foo(true)))
    }

}