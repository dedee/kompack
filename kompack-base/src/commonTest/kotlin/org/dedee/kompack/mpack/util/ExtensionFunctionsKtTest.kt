package org.dedee.kompack.mpack.util

import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class ExtensionFunctionsKtTest {

    @Test
    fun `HEX String extension function simple test`() {
        assertEquals(
            "000102030405060708090a0b0c0d0e0f10",
            byteArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16).hex().lowercase()
        )
    }

    @Test
    fun dehex() {
        assertContentEquals(byteArrayOf(0x0a, 0x12, 0xfe.toByte()), "0a12fe".dehex())
    }
}