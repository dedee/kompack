package com.wunderbee.kompack.mpack.pack

import java.io.OutputStream

class StreamPacker(out: OutputStream) : Packer(SinkStream(out))