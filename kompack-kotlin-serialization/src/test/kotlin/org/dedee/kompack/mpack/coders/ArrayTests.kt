package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ArrayTests {

    @Serializable
    data class Routes(
        val name: String,
        val cities: List<String>,
    )

    @Test
    fun `Encode and decode `() {
        val r = Routes(
            "engineer", listOf("1", "2", "3")
        )
        val r2 = MessagePackDecoder.decodeFromByteArray<Routes>(MessagePackEncoder.encodeToByteArray(r))
        assertEquals(r, r2)
    }


}