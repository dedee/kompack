package com.wunderbee.kompack.cdb

class CdbHash {

    companion object {
        private const val SEED = 5381L
        private const val MASK = 0x00000000ffffffffL

        fun hash(key: ByteArray): Int {
            var h: Long = SEED
            for (b in key) {
                val k = b + 0x100 and 0xff
                val l = h shl 5
                h += l and MASK
                h = h and MASK
                h = h xor k.toLong()
            }
            return (h and MASK).toInt()
        }
    }

}