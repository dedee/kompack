package org.dedee.kompack.mpack.unpack

fun interface TypeUnpacker<T> {
    fun unpack(source: Source): T?
}

