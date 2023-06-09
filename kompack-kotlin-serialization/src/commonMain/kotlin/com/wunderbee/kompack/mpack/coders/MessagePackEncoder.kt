package com.wunderbee.kompack.mpack.coders

import com.wunderbee.kompack.mpack.pack.InMemoryPacker
import com.wunderbee.kompack.mpack.pack.Packer
import com.wunderbee.kompack.mpack.pack.Sink
import com.wunderbee.kompack.mpack.pack.build
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

@OptIn(ExperimentalSerializationApi::class)
class MessagePackEncoder(
    val packer: Packer = InMemoryPacker(1_000)
) : AbstractEncoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()

    override fun encodeNull() {
        packer.packNil()
    }

    override fun encodeNotNullMark() {
        // msg pack includes null handling nothing to do
    }

    override fun encodeBoolean(value: Boolean) {
        packer.pack(value)
    }

    override fun encodeByte(value: Byte) {
        packer.pack(value.toInt())
    }

    override fun encodeChar(value: Char) {
        packer.pack(value.toString())
    }

    override fun encodeDouble(value: Double) {
        packer.pack(value)
    }

    override fun encodeFloat(value: Float) {
        packer.pack(value)
    }

    override fun encodeInt(value: Int) {
        packer.pack(value)
    }

    override fun encodeLong(value: Long) {
        packer.pack(value)
    }

    override fun encodeShort(value: Short) {
        packer.pack(value.toInt())
    }

    override fun encodeString(value: String) {
        packer.pack(value)
    }

    override fun encodeValue(value: Any) {
        packer.pack(value)
    }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        encodeInt(collectionSize)
        return this
    }

    companion object {
        fun <T> encodeToByteArray(
            serializer: SerializationStrategy<T>,
            value: T,
            messagePackEncoder: MessagePackEncoder = MessagePackEncoder()
        ): ByteArray {
            messagePackEncoder.encodeSerializableValue(serializer, value)
            return messagePackEncoder.packer.build()
        }

        inline fun <reified T> encodeToByteArray(value: T) = encodeToByteArray(serializer(), value)

        inline fun <reified T> encodeToByteArray(value: T, packer: Packer) =
            encodeToByteArray(serializer(), value, MessagePackEncoder(packer))


        fun <T> encodeToStream(
            serializer: SerializationStrategy<T>,
            value: T,
            messagePackEncoder: MessagePackEncoder,
        ) {
            messagePackEncoder.encodeSerializableValue(serializer, value)
        }

        inline fun <reified T> encodeToStream(value: T, sink: Sink) =
            encodeToStream(serializer(), value, MessagePackEncoder(Packer(sink)))
    }
}