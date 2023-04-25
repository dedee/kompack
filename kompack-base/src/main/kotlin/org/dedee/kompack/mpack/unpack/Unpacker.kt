package org.dedee.kompack.mpack.unpack

import kotlin.reflect.KClass


class Unpacker(val source: Source) {

    private val intTypeUnpacker = IntTypeUnpacker()
    private val longTypeUnpacker = LongTypeUnpacker()
    private val uLongTypeUnpacker = ULongTypeUnpacker()
    private val booleanTypeUnpacker = BooleanTypeUnpacker()
    private val binaryTypeUnpacker = BinaryTypeUnpacker()
    private val stringTypeUnpacker = StringTypeUnpacker()
    private val floatTypeUnpacker = FloatTypeUnpacker()
    private val doubleTypeUnpacker = DoubleTypeUnpacker()
    private val arrayTypeUnpacker = ArrayTypeUnpacker()
    private val mapTypeUnpacker = MapTypeUnpacker()

    val typeUnpackerMap: Map<KClass<*>, TypeUnpacker<*>> = mapOf(
        Pair(Int::class, IntTypeUnpacker()),
        Pair(Long::class, LongTypeUnpacker()),
    )


    inline fun <reified T : Any> unpakk(): T? {
        return when {
            isNil() -> null

            T::class == Int::class -> unpackInt() as T
            T::class == Long::class -> unpackLong() as T
            T::class == ULong::class -> unpackULong() as T

            T::class == Boolean::class -> unpackBoolean() as T
            T::class == ByteArray::class -> unpackBinary() as T
            T::class == String::class -> unpackString() as T
            T::class == Float::class -> unpackFloat() as T
            T::class == Double::class -> unpackDouble() as T

            T::class == Array::class -> unpackArray() as T
            T::class == Map::class -> unpackMap() as T

            else -> TODO()
        }
    }

    inline fun <reified T : Any> unpakk2(): T? {
        if (isNil()) {
            return null
        }
        return typeUnpackerMap[T::class]!!.unpack(source) as T?
    }

    fun unpackInt(): Int? {
        return intTypeUnpacker.unpack(source)
    }

    fun unpackLong(): Long? {
        return longTypeUnpacker.unpack(source)
    }

    fun unpackULong(): ULong? {
        return uLongTypeUnpacker.unpack(source)
    }

    fun unpackBoolean(): Boolean? {
        return booleanTypeUnpacker.unpack(source)
    }

    fun unpackBinary(): ByteArray? {
        return binaryTypeUnpacker.unpack(source)
    }

    fun unpackString(): String? {
        return stringTypeUnpacker.unpack(source)
    }

    fun unpackFloat(): Float? {
        return floatTypeUnpacker.unpack(source)
    }

    fun unpackDouble(): Double? {
        return doubleTypeUnpacker.unpack(source)
    }

    fun unpackArray(): Array<Any>? {
        return arrayTypeUnpacker.unpack(source)
    }

    fun unpackMap(): Map<Any, Any?>? {
        return mapTypeUnpacker.unpack(source)
    }

    fun unpackNil() {
        val b = source.pullByte()
        if (b != 0x0c0) {
            throw Exception("Could not unpack, unknown type $b")
        }
    }

    fun isNil(): Boolean {
        val b = source.pullByte()
        source.back()
        return b == 0x0c0
    }

    fun unpack(): Any? {
        val b = source.pullByte()
        source.back()

        when (b) {
            in 0x00..0x7f -> {
                // positive fixint
                return intTypeUnpacker.unpack(source)
            }

            in 0x80..0x8f -> {
                // fixmap
                return mapTypeUnpacker.unpack(source)
            }

            in 0x90..0x9f -> {
                // fixarray
                return arrayTypeUnpacker.unpack(source)
            }

            in 0xa0..0xbf -> {
                // fixstr
                return stringTypeUnpacker.unpack(source)
            }

            in 0xe0..0xff -> {
                return intTypeUnpacker.unpack(source)
            }

            else -> {
                return when (b) {
                    0x0c0 -> null
                    0xc1 -> TODO("NOT USED")
                    0xc2, 0xc3 -> booleanTypeUnpacker.unpack(source)
                    0xc4, 0xc5, 0xc6 -> binaryTypeUnpacker.unpack(source)
                    0xc7, 0xc8, 0xc9 -> TODO("EXT")
                    0xca, 0xcb -> floatTypeUnpacker.unpack(source)
                    0xcc, 0xcd -> intTypeUnpacker.unpack(source)
                    0xce -> longTypeUnpacker.unpack(source)
                    0xcf -> uLongTypeUnpacker.unpack(source)
                    0xd0, 0xd1, 0xd2 -> intTypeUnpacker.unpack(source)
                    0xd3 -> longTypeUnpacker.unpack(source)
                    0xd4, 0xd5, 0xd6, 0xd7, 0xd8 -> TODO("EXT")
                    0xd9, 0xda, 0xdb -> stringTypeUnpacker.unpack(source)
                    0xdc, 0xdd -> arrayTypeUnpacker.unpack(source)
                    0xde, 0xdf -> mapTypeUnpacker.unpack(source)
                    else -> throw Exception()
                }
            }
        }
    }

}