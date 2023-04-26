package org.dedee.kompack.mpack.pack

import org.dedee.kompack.mpack.util.hex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class SerializerFloatTests {
    private val packer = InMemoryPacker(10)

    // float 32 stores a floating point number in IEEE 754 single precision floating point number format:
    //+--------+--------+--------+--------+--------+
    //|  0xca  |XXXXXXXX|XXXXXXXX|XXXXXXXX|XXXXXXXX|
    //+--------+--------+--------+--------+--------+
    //
    //float 64 stores a floating point number in IEEE 754 double precision floating point number format:
    //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
    //|  0xcb  |YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|
    //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
    //
    //where
    //*
    //* YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY is a big-endian
    //  IEEE 754 double precision floating point number
    @Test
    fun `float double NIL`() {
        assertEquals("c0", packer.packNil().build().hex())
    }


    @Test
    fun `float 32 stores a floating point number in IEEE 754 single precision floating point number format`() {
        // float 32 stores a floating point number in IEEE 754 single precision floating point number format:
        // +--------+--------+--------+--------+--------+
        // |  0xca  |XXXXXXXX|XXXXXXXX|XXXXXXXX|XXXXXXXX|
        // +--------+--------+--------+--------+--------+
        // XXXXXXXX_XXXXXXXX_XXXXXXXX_XXXXXXXX is a big-endian IEEE 754 single precision floating point number.
        assertEquals("ca3dfbe76d", packer.pack(0.123f).build().hex())
    }

    @Test
    fun `float 64 stores a floating point number in IEEE 754 double precision floating point number format`() {
        //float 64 stores a floating point number in IEEE 754 double precision floating point number format:
        //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
        //|  0xcb  |YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|YYYYYYYY|
        //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
        // YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY_YYYYYYYY is a big-endian
        // IEEE 754 double precision floating point number
        assertEquals("cb3fbf7ced916872b0", packer.pack(0.123).build().hex())
    }

}