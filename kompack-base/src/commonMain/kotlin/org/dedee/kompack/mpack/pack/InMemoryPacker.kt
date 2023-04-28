package org.dedee.kompack.mpack.pack

class InMemoryPacker(size: Int = 1024) : Packer(
    SinkInMemory(
        ByteArray(size)
    )
)