package com.wunderbee.kompack.mpack.util

import kotlin.test.Test
import kotlin.test.assertEquals

class SelfGrowingInMemorySinkTest {

    @Test
    fun addByte() {
        assertEquals("0001", SelfGrowingInMemorySink(0, 1).addByte(0).addByte(1).toHexString())
    }
}