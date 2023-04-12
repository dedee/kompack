package org.dedee.kompack.mpack.coders

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer
import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker

@OptIn(ExperimentalSerializationApi::class)
class MessagePackDecoder(
    private val unpacker: Unpacker,
    private var elementsCount: Int = 0
) : AbstractDecoder() {

    private var elementIndex = 0
    override val serializersModule: SerializersModule = EmptySerializersModule()

    override fun decodeValue(): Any {
        return unpacker.unpack()!!
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        if (elementIndex == descriptor.elementsCount) return CompositeDecoder.DECODE_DONE
        return elementIndex++
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder =
        MessagePackDecoder(unpacker, descriptor.elementsCount)

    override fun decodeCollectionSize(descriptor: SerialDescriptor): Int = decodeInt().also { elementsCount = it }


    override fun decodeSequentially(): Boolean = true

    // TEST NULLABILITY
    override fun decodeNotNullMark(): Boolean {
        return !unpacker.isNil()
    }

    // TEST
    override fun decodeNull(): Nothing? {
        unpacker.unpackNil()
        return null
    }

    companion object {
        fun <T> decodeFromByteArray(b: ByteArray, deserializer: DeserializationStrategy<T>): T {
            val decoder = MessagePackDecoder(Unpacker(Source(b)))
            return decoder.decodeSerializableValue(deserializer)
        }

        inline fun <reified T> decodeFromByteArray(b: ByteArray): T = decodeFromByteArray(b, serializer())
    }
}