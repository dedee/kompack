package org.dedee.kompack.mpack.unpack

import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SourceTest {

    @Test
    fun pullUInt64() {
        assertEquals(ULong.MAX_VALUE, Source("ffffffffffffffff".dehex()).pullUInt64())
        assertEquals(1uL, Source("0000000000000001".dehex()).pullUInt64())
    }

    @Test
    fun pull128ULong() {
        assertEquals(128uL, Source("00-00-00-00--00-00-00-80".dehex()).pullUInt64())
    }
}