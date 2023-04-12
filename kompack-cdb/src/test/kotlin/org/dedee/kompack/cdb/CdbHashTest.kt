package org.dedee.kompack.cdb

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class CdbHashTest {

    @Test
    fun foo() {
        assertEquals(5381, CdbHash.hash(byteArrayOf()))
        assertEquals(177573, CdbHash.hash(byteArrayOf(0)))
        assertEquals(5859908, CdbHash.hash(byteArrayOf(0, 1)))
        assertEquals(193376966, CdbHash.hash(byteArrayOf(0, 1, 2)))
    }

}