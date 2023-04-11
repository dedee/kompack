package org.dedee.kompack.mpack.unpack

interface TypeUnpacker<T> {
    fun unpack(source: Source): T?
}

