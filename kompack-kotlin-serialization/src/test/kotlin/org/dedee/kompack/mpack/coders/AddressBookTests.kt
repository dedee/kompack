package org.dedee.kompack.mpack.coders

import kotlinx.serialization.Serializable
import org.dedee.kompack.mpack.util.hex
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
        val stamp: Long,
    )

    @Serializable
    data class AddressBook(
        val persons: List<Person>
    )

    private val john = Person(
        "John Doe",
        listOf(Phone("mobile", "+33 3249094 423434")),
        Address("Marktplatz 8", 12345, "Berlin"),
        "1990-01-01", 4324343L
    )

    private val harry = Person(
        "Harry Pott",
        listOf(Phone("work", "+41 323928 44643000"), Phone("mobile", "+41 12308 209838")),
        Address("State St.", 76353, "St Barbara"),
        "1987-12-24", 3432L
    )

    @Test
    fun `Encode and decode addressbook with list`() {
        val addressBook1 = AddressBook(listOf(john, harry))
        val b = MessagePackEncoder.encodeToByteArray(addressBook1)
        println(b.hex())
        val addressBook2 = MessagePackDecoder.decodeFromByteArray<AddressBook>(b)
        println(addressBook2)
        assertEquals(addressBook1, addressBook2)
    }

}