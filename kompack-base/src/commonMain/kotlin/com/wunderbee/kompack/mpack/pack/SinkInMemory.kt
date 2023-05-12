//package com.wunderbee.kompack.mpack.pack
//
//import com.wunderbee.kompack.mpack.util.hex
//
//
//class SinkInMemory(private val dest: ByteArray) : Sink {
//
//    private var length: Int = 0
//
//    override fun addByte(x: Int): Sink {
//        dest[length++] = (x and 0xff).toByte()
//        return this
//    }
//
//    override fun addInt16(x: Int): Sink {
//        dest[length++] = (x ushr 8 and 0xff).toByte()
//        dest[length++] = (x and 0xff).toByte()
//        return this
//    }
//
//    override fun addInt32(x: Int): Sink {
//        dest[length++] = (x ushr 24 and 0xff).toByte()
//        dest[length++] = (x ushr 16 and 0xff).toByte()
//        dest[length++] = (x ushr 8 and 0xff).toByte()
//        dest[length++] = (x and 0xff).toByte()
//        return this
//    }
//
//    override fun addInt64(x: Long): Sink {
//        dest[length++] = (x ushr 56 and 0xffL).toByte()
//        dest[length++] = (x ushr 48 and 0xffL).toByte()
//        dest[length++] = (x ushr 40 and 0xffL).toByte()
//        dest[length++] = (x ushr 32 and 0xffL).toByte()
//        dest[length++] = (x ushr 24 and 0xffL).toByte()
//        dest[length++] = (x ushr 16 and 0xffL).toByte()
//        dest[length++] = (x ushr 8 and 0xffL).toByte()
//        dest[length++] = (x and 0xffL).toByte()
//        return this
//    }
//
//    override fun addUInt64(x: ULong): Sink {
//        addInt64(x.toLong())
//        return this
//    }
//
//    override fun addBytes(src: ByteArray, offset: Int, len: Int): Sink {
//        src.copyInto(this.dest, length, offset, offset + len)
//        length += len
//        return this
//    }
//
//    override fun addBytes(src: ByteArray): Sink {
//        addBytes(src, 0, src.size)
//        return this
//    }
//
//    override fun flush() {
//        // Noting to do here
//    }
//
//    fun build(): ByteArray {
//        val l = length
//        length = 0
//        return dest.copyOf(l)
//    }
//
//    fun toHexString(): String {
//        return build().hex()
//    }
//}