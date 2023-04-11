package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SerializerArrayTests {
    private val dest = ByteArray(1000)
    private val s = Packer(Sink(dest))

    @Test
    fun `empty list`() {
        assertEquals("90", s.pack(emptyArray()).build().hex())
    }

    @Test
    fun `fixarray stores an array whose length is upto 15 elements`() {
        // fixarray stores an array whose length is upto 15 elements:
        // +--------+~~~~~~~~~~~~~~~~~+
        // |1001XXXX|    N objects    |
        // +--------+~~~~~~~~~~~~~~~~~+
        val l = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        assertEquals("9f0102030405060708090a0b0c0d0e0f", s.pack(l).build().hex())
    }


    //
    //array 16 stores an array whose length is upto (2^16)-1 elements:
    //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
    //|  0xdc  |YYYYYYYY|YYYYYYYY|    N objects    |
    //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
    //
    //array 32 stores an array whose length is upto (2^32)-1 elements:
    //+--------+--------+--------+--------+--------+~~~~~~~~~~~~~~~~~+
    //|  0xdd  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|    N objects    |
    //+--------+--------+--------+--------+--------+~~~~~~~~~~~~~~~~~+
    //
    //where
    //* XXXX is a 4-bit unsigned integer which represents N
    //* YYYYYYYY_YYYYYYYY is a 16-bit big-endian unsigned integer which represents N
    //* ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ_ZZZZZZZZ is a 32-bit big-endian unsigned integer which represents N
    //* N is the size of an array
}