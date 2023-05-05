package com.wunderbee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import kotlin.test.Test
import kotlin.test.assertContentEquals

class MessagePackEncoderTest {

    @Serializable
    data class Foo(
        val b: Boolean
    )

    @Test
    fun `Encode one boolean field`() {
        assertContentEquals(byteArrayOf(0xc3.toByte()), MessagePackEncoder.encodeToByteArray(Foo(true)))
    }

}