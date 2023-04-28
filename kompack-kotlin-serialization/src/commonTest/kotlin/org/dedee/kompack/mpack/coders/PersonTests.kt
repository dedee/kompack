package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.dedee.kompack.mpack.util.hex
import kotlin.test.Test
import kotlin.test.assertEquals

class PersonTests {

    @Serializable
    data class Address(
        val street: String,
        val zip: Int,
        val city: String,
    )

    @Serializable
    data class Phone(
        val type: String,
        val number: String,
    )

    @Serializable
    data class Person(
        val name: String,
        val phone: List<Phone>,
        val address: Address,
        val birthday: String,
    )

    @Test
    fun `Encode and decode simple`() {
        val person = Person(
            "John Doe",
            listOf(
                Phone("mobile", "+49 1283 309142445"),
            ),
            Address("Marktplatz 8", 12345, "Berlin"),
            "1990-01-01"
        )
        val b = MessagePackEncoder.encodeToByteArray(person)
        println(b.hex())
        val person2 = MessagePackDecoder.decodeFromByteArray<Person>(b)
        println(person2)
        assertEquals(person, person2)
    }

    @Test
    fun `Encode and decode with more than one phone number`() {
        val person = Person(
            "John Doe",
            listOf(
                Phone("mobile", "+49 1283 309142445"),
                Phone("work", "+49 38338 3873737373"),
            ),
            Address("Marktplatz 8", 12345, "Berlin"),
            "1990-01-01"
        )
        val b = MessagePackEncoder.encodeToByteArray(person)
        println(b.hex())
        val person2 = MessagePackDecoder.decodeFromByteArray<Person>(b)
        println(person2)
        assertEquals(person, person2)
    }
}