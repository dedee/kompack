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