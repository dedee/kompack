package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AddressBookTests {

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

    @Serializable
    data class AddressBook(
        val persons: List<Person>
    )

    @Serializable
    data class Job(
        val name: String,
        val description: String? = null,
        val date: String,
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

    @Disabled
    @Test
    fun `Encode and decode addressbook with list`() {
        val addressBook = AddressBook(
            listOf(
                Person(
                    "John Doe",
                    listOf(
                        Phone("mobile", "+49 1283 309142445"),
                        Phone("work", "+49 38338 3873737373"),
                    ),
                    Address("Marktplatz 8", 12345, "Berlin"),
                    "1990-01-01"
                ),
                Person(
                    "Harry Pott",
                    listOf(
                        Phone("mobile", "+49 22230 3914265"),
                        Phone("work", "+49 653355 3277766"),
                    ),
                    Address("State St", 76353, "St Barbara"),
                    "1987-12-24"
                )
            )
        )
        val b = MessagePackEncoder.encodeToByteArray(addressBook)
        println(b.hex())
        val addressBook2 = MessagePackDecoder.decodeFromByteArray<AddressBook>(b)
        println(addressBook2)
        assertEquals(addressBook, addressBook2)
    }

    @Test
    fun `Encode and decode with nulls`() {
        val job = Job("engineer", null, "2023-04-01")
        val b = MessagePackEncoder.encodeToByteArray(job)
        val job2 = MessagePackDecoder.decodeFromByteArray<Job>(b)
        println(job2)
        assertEquals(job, job2)
    }
}