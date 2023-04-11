package org.dedee.kompack.mpack.pack

interface TypePacker<T> {
    fun pack(obj: T, sink: Sink)

}