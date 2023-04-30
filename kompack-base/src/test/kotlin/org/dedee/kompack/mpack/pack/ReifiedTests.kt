package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Test

class ReifiedTests {

    @Test
    fun foo() {
        println(Packer(SinkInMemory(ByteArray(20)))
            .pakk(1)
            .pakk(2L)
            .pakk("bumm")
            .build().hex())
    }
}