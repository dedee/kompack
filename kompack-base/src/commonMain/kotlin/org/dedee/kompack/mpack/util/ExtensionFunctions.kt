package org.dedee.kompack.mpack.util


private val CHAR_HEX_MAP = charArrayOf(
    '0', '1', '2', '3',
    '4', '5', '6', '7',
    '8', '9', 'a', 'b',
    'c', 'd', 'e', 'f'
)

fun ByteArray.hex(): String {
    val sb = StringBuilder()
    for (i in indices) {
        sb.append(CHAR_HEX_MAP[this[i].toInt() shr 4 and 0x0f])
        sb.append(CHAR_HEX_MAP[this[i].toInt() and 0x0f])
    }
    return sb.toString()
}

fun String.dehex(): ByteArray {
    val s2 = this.replace("-", "")
    val ba = ByteArray(s2.length / 2)
    for (i in 0 until ba.size) {
        val c1 = s2[i * 2]
        val c2 = s2[i * 2 + 1]
        ba[i] = c1.digitToInt(16).shl(4).or(c2.digitToInt(16)).toByte()
    }
    return ba
}