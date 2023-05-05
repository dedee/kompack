package com.wunderbee.kompack.mpack.pack

fun interface TypePacker<T> {
    fun pack(obj: T, sink: Sink)
}