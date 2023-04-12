package org.dedee.kompack.cdb

class CdbHash {

    companion object {
        fun hash(key: ByteArray): Int {
            var h: Long = 5381
            for (b in key) {
                val l = h shl 5
                h += l and 0x00000000ffffffffL
                h = h and 0x00000000ffffffffL
                val k = b + 0x100 and 0xff
                h = h xor k.toLong()
            }
            return (h and 0x00000000ffffffffL).toInt()
        }
    }

}