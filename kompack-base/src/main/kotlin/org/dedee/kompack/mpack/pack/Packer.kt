package org.dedee.kompack.mpack.pack

class Packer(private val sink: Sink) {

    private val booleanTypePacker = BooleanTypePacker()
    private val intTypePacker = IntTypePacker()
    private val longTypePacker = LongTypePacker()
    private val ulongTypePacker = ULongTypePacker()
    private val floatTypePacker = FloatTypePacker()
    private val doubleTypePacker = DoubleTypePacker()
    private val stringTypePacker = StringTypePacker()
    private val binTypePacker = BinTypePacker()
    private val arrayTypePacker = ArrayTypePacker()
    private val mapTypePacker = MapTypePacker()

    fun build() = sink.build()

    fun pack(o: Any?): Packer {
        if (o == null) {
            packNil()
        } else if (o is Int) {
            pack(o)
        } else if (o is Long) {
            if (o <= Int.MAX_VALUE) {
                pack(o.toInt())
            } else {
                pack(o)
            }
        } else if (o is ULong) {
            ulongTypePacker.pack(o, sink)
        } else if (o is Boolean) {
            pack(o)
        } else if (o is Float) {
            pack(o)
        } else if (o is Double) {
            pack(o)
        } else if (o is String) {
            pack(o)
        } else if (o is ByteArray) {
            pack(o)
        } else if (o is Array<*> && o.isArrayOf<Any>()) {
            pack(o as Array<Any>)
        } else if (o is Map<*, *>) {
            pack(o as Map<Any, Any?>)
        } else {
            TODO("Type of $o ?? ${o::class}")
        }
        return this
    }

    fun pack(b: Boolean): Packer {
        booleanTypePacker.pack(b, sink)
        return this
    }

    fun pack(i: Int): Packer {
        intTypePacker.pack(i, sink)
        return this
    }

    fun pack(l: Long): Packer {
        longTypePacker.pack(l, sink)
        return this
    }

// not allowed
//    fun pack(ul: ULong): Packer {
//        ulongTypePacker.pack(ul, sink)
//        return this
//    }


    fun pack(f: Float): Packer {
        floatTypePacker.pack(f, sink)
        return this
    }

    fun pack(d: Double): Packer {
        doubleTypePacker.pack(d, sink)
        return this
    }

    fun pack(s: String): Packer {
        stringTypePacker.pack(s, sink)
        return this
    }

    fun pack(b: ByteArray): Packer {
        binTypePacker.pack(b, sink)
        return this
    }

    fun pack(l: Array<Any>): Packer {
        arrayTypePacker.pack(l, sink)
        return this
    }

    fun pack(m: Map<Any, Any?>): Packer {
        mapTypePacker.pack(m, sink)
        return this
    }

    fun packNil(): Packer {
        sink.addByte(0xc0)
        return this
    }
}