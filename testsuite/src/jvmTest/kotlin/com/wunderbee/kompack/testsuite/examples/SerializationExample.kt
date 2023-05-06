package com.wunderbee.kompack.testsuite.examples

import kotlinx.serialization.Serializable
import com.wunderbee.kompack.mpack.coders.MessagePackEncoder
import com.wunderbee.kompack.mpack.pack.SinkStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream


class SerializationExample {

    @Serializable
    data class Entry(val name: String, val street: String, val zip: Int, val city: String)

    @Serializable
    data class AddressBook(val entries: List<Entry>)


    fun `Serialization API in memory`() {
        val addressBook = AddressBook(
            listOf(
                Entry("Heinz A. Mueller", "State St. 8", 31232, "Kalamazoo"),
                Entry("Max Mustermann", "Schlossstr. 8", 78334, "Ludwigsburg"),
            )
        )

        val encodedByteArray = MessagePackEncoder.encodeToByteArray(addressBook)
        File("test1.dat").writeBytes(encodedByteArray)
    }

    fun `Serialization API stream based`() {
        val addressBook = AddressBook(
            listOf(
                Entry("Heinz A. Mueller", "State St. 8", 31232, "Kalamazoo"),
                Entry("Max Mustermann", "Schlossstr. 8", 78334, "Ludwigsburg"),
            )
        )

        SinkStream(BufferedOutputStream(FileOutputStream("test2.dat"))).use { out ->
            MessagePackEncoder.encodeToStream(addressBook, out)
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SerializationExample().`Serialization API in memory`()
            SerializationExample().`Serialization API stream based`()
        }
    }

}


