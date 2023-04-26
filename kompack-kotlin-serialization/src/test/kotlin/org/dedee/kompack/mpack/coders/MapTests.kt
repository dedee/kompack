package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class MapTests {

    @Serializable
    data class Routes(
        val name: String,
        val cities: Map<String, String>,
    )

    @Test
    fun `Encode and decode `() {
        val r = Routes(
            "engineer", mapOf(Pair("A", "B"), Pair("C", "D"))
        )
        val r2 = MessagePackDecoder.decodeFromByteArray<Routes>(MessagePackEncoder.encodeToByteArray(r))
        assertEquals(r, r2)
    }


}