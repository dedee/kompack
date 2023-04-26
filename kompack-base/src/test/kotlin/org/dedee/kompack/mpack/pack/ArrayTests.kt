package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArrayTests {

    @Test
    fun `empty list`() {
        assertEquals("90", InMemoryPacker(10).pack(emptyArray()).build().hex())
    }

    @Test
    fun `fixarray stores an array whose length is upto 15 elements`() {
        // fixarray stores an array whose length is upto 15 elements:
        // +--------+~~~~~~~~~~~~~~~~~+
        // |1001XXXX|    N objects    |
        // +--------+~~~~~~~~~~~~~~~~~+
        val l = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15)
        assertEquals("9f0102030405060708090a0b0c0d0e0f", InMemoryPacker(50).pack(l).build().hex())
    }

    @Test
    fun simpleArray() {
        val a = arrayOf(1, 2, 3, 4)
        val encoded = InMemoryPacker(50).pack(a).build()
        val unpackedArray = InMemoryUnpacker(encoded).unpackArray()
        assertEquals(4, unpackedArray!!.size)
    }

    @Test
    fun arrayInArray() {
        val a = arrayOf(arrayOf(1, 2, 3), arrayOf(3, 4, 5, 6))
        val encoded = InMemoryPacker(50).pack(a).build()
        println(encoded.hex())
        val unpackedArray = InMemoryUnpacker(encoded).unpackArray()
        assertEquals(2, unpackedArray!!.size)
        assertEquals(3, (unpackedArray[0] as Array<*>).size)
        assertEquals(4, (unpackedArray[1] as Array<*>).size)
    }


    @Test
    fun `array 16 stores an array whose length is upt`() {
        //array 16 stores an array whose length is upto (2^16)-1 elements:
        //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        //|  0xdc  |YYYYYYYY|YYYYYYYY|    N objects    |
        //+--------+--------+--------+~~~~~~~~~~~~~~~~~+
        val l = Array(1024) { 0 }
        val c = InMemoryPacker(1100).pack(l).build()
        val l2: Array<*> = InMemoryUnpacker(c).unpack() as Array<*>
        assertEquals(1024, l2.size)
    }


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