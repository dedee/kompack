package org.dedee.kompack.testsuite.compatibility

import com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack
import kotlinx.serialization.encodeToByteArray
import org.dedee.kompack.mpack.coders.MessagePackEncoder
import org.dedee.kompack.mpack.pack.InMemoryPacker
import org.dedee.kompack.testsuite.stresstests.BookUtil

class Compat1 {

    init {
        val book = BookUtil.createBook()

        println(MsgPack.encodeToByteArray(book).size)
        println(MessagePackEncoder.encodeToByteArray(book, InMemoryPacker(1_000_000)).size)

        // TODO check produces different sizes, seems incompatible
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            Compat1()
        }

    }
}