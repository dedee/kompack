package com.wunderbee.kompack.mpack.pack

import com.wunderbee.kompack.mpack.util.SelfGrowingInMemorySink

class InMemoryPacker(size: Int = 1024, regrowExtendSize: Int = 1024) :
    Packer(SelfGrowingInMemorySink(size, regrowExtendSize))
