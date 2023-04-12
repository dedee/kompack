package org.dedee.kompack.mpack.coders

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.Sink

@OptIn(ExperimentalSerializationApi::class)
class MessagePackEncoder(
    val packer: Packer = Packer(Sink(ByteArray(1_000)))
) : AbstractEncoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()

//    override fun encodeBoolean(value: Boolean) {
//        packer.pack(value)
//    }

    override fun encodeNull() {
        packer.packNil()
    }

//    override fun encodeNotNullMark() {
//        packer.pack(1)
//    }


    override fun encodeValue(value: Any) {
        packer.pack(value)
    }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        encodeInt(collectionSize)
        return this
    }

    private fun build(): ByteArray {
        return packer.build()
    }

    companion object {
        fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
            val messagePackEncoder = MessagePackEncoder()
            messagePackEncoder.encodeSerializableValue(serializer, value)
            return messagePackEncoder.build()
        }

        inline fun <reified T> encodeToByteArray(value: T) = encodeToByteArray(serializer(), value)
    }
}