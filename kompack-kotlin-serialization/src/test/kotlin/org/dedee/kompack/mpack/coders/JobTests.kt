package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JobTests {

    @Serializable
    data class Job(
        val name: String,
        val description: String? ,
        val date: String? ,
    )

    @Test
    fun `Encode and decode `() {
        val job = Job("engineer", "description", "2023-04-01")
        val job2 = MessagePackDecoder.decodeFromByteArray<Job>(MessagePackEncoder.encodeToByteArray(job))
        assertEquals(job, job2)
    }

    @Test
    fun `Encode and decode with nulls`() {
        val job = Job("engineer", null, null)
        val job2 = MessagePackDecoder.decodeFromByteArray<Job>(MessagePackEncoder.encodeToByteArray(job))
        assertEquals(job, job2)
    }
}