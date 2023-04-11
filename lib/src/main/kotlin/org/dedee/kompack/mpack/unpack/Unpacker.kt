package org.dedee.kompack.mpack.unpack

class Unpacker(private val source: Source) {

    private val intTypeUnpacker = IntTypeUnpacker()
    private val longTypeUnpacker = LongTypeUnpacker()
    private val booleanTypeUnpacker = BooleanTypeUnpacker()
    private val binaryTypeUnpacker = BinaryTypeUnpacker()
    private val stringTypeUnpacker = StringTypeUnpacker()
    private val floatTypeUnpacker = FloatTypeUnpacker()
    private val doubleTypeUnpacker = DoubleTypeUnpacker()
    private val arrayTypeUnpacker = ArrayTypeUnpacker()
    private val mapTypeUnpacker = MapTypeUnpacker()

    fun unpackInt(): Int? {
        return intTypeUnpacker.unpack(source)
    }

    fun unpackLong(): Long? {
        return longTypeUnpacker.unpack(source)
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

    fun unpack(source: Source): Any? {
        val b = source.pullByte()
        source.back()

        when (b) {
            in 0x00..0x7f -> {
                // positive fixint
                return intTypeUnpacker.unpack(source)
            }

            in 0x80..0x8f -> {
                // fixmap
                TODO()
            }

            in 0x90..0x9f -> {
                // fixarray
                arrayTypeUnpacker.unpack(source)
            }

            in 0xa0..0xbf -> {
                // fixstr
                return stringTypeUnpacker.unpack(source)
            }

            in 0xe0..0xff -> {
                return intTypeUnpacker.unpack(source)
            }

            else -> {
                when (b) {
                    0x0c0 -> return null
                    0xc1 -> TODO("NOT USED")
                    0xc2, 0xc3 -> return booleanTypeUnpacker.unpack(source)
                    0xc4, 0xc5, 0xc6 -> return binaryTypeUnpacker.unpack(source)
                    0xc7, 0xc8, 0xc9 -> TODO("EXT")
                    0xca, 0xcb -> floatTypeUnpacker.unpack(source)
                    0xcc, 0xcd -> intTypeUnpacker.unpack(source)
                    0xce, 0xcf -> longTypeUnpacker.unpack(source)
                    0xd0, 0xd1, 0xd2 -> intTypeUnpacker.unpack(source)
                    0xd3 -> longTypeUnpacker.unpack(source)
                    0xd4, 0xd5, 0xd6, 0xd7, 0xd8 -> TODO("EXT")
                    0xd9, 0xda, 0xdb -> stringTypeUnpacker.unpack(source)
                    0xdc, 0xdd -> arrayTypeUnpacker.unpack(source)
                    0xde, 0xdf -> mapTypeUnpacker.unpack(source)
                }
            }
        }

        TODO("Not yet implemented")
    }


}