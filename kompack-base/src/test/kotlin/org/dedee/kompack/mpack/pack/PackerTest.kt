package org.dedee.kompack.mpack.pack

import org.junit.jupiter.api.Test

class PackerTest {
    private val dest = ByteArray(10)
    private val s = Sink(dest)

    @Test
    fun pack() {
        val p = Packer(s)
        p.pack(1)
        p.pack(10L)
//        p.pack(listOf(1,2))
    }
}