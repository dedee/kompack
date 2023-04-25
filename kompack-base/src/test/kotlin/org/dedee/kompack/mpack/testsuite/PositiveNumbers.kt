package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PositiveNumbers {

    @Test
    fun `20 number positive`() {
        assertNull(Unpacker(Source("c0".dehex())).unpackInt())
        assertNull(Unpacker(Source("c0".dehex())).unpackLong())

        //  "20.number-positive.yaml": [

        //    {
        //      "number": 0,
        //      "msgpack": [
        //        "00",
        //        "cc-00",
        //        "cd-00-00",
        //        "ce-00-00-00-00",
        //        "cf-00-00-00-00-00-00-00-00",
        //        "d0-00",
        //        "d1-00-00",
        //        "d2-00-00-00-00",
        //        "d3-00-00-00-00-00-00-00-00",
        //        "ca-00-00-00-00",
        //        "cb-00-00-00-00-00-00-00-00"
        //      ]
        //    },
        assertEquals(0, Unpacker(Source("00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("cc-00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("cd-00-00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("ce-00-00-00-00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("ce-00-00-00-00".dehex())).unpackLong())
        assertEquals(0uL, Unpacker(Source("cf-00-00-00-00-00-00-00-00".dehex())).unpackULong())
        assertEquals(0, Unpacker(Source("d0-00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("d0-00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("d1-00-00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("d2-00-00-00-00".dehex())).unpackInt())
        assertEquals(0, Unpacker(Source("d3-00-00-00-00-00-00-00-00".dehex())).unpackLong())
        assertEquals(0.0, Unpacker(Source("ca-00-00-00-00".dehex())).unpackFloat()!!.toDouble(), 0.001)
        assertEquals(0.0, Unpacker(Source("cb-00-00-00-00-00-00-00-00".dehex())).unpackDouble()!!, 0.001)

        //    {
        //      "number": 1,
        //      "msgpack": [
        //        "01",
        //        "cc-01",
        //        "cd-00-01",
        //        "ce-00-00-00-01",
        //        "cf-00-00-00-00-00-00-00-01",
        //        "d0-01",
        //        "d1-00-01",
        //        "d2-00-00-00-01",
        //        "d3-00-00-00-00-00-00-00-01",
        //        "ca-3f-80-00-00",
        //        "cb-3f-f0-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(1, Unpacker(Source("01".dehex())).unpackInt())
        assertEquals(1, Unpacker(Source("cc-01".dehex())).unpackInt())
        assertEquals(1, Unpacker(Source("cd-00-01".dehex())).unpackInt())
        assertEquals(1, Unpacker(Source("ce-00-00-00-01".dehex())).unpackInt())
        assertEquals(1, Unpacker(Source("ce-00-00-00-01".dehex())).unpackLong())
        assertEquals(1uL, Unpacker(Source("cf-00-00-00-00-00-00-00-01".dehex())).unpackULong())
        assertEquals(1, Unpacker(Source("d0-01".dehex())).unpackInt())

        assertEquals(1, Unpacker(Source("d0-01".dehex())).unpackInt())
        assertEquals(1, Unpacker(Source("d1-00-01".dehex())).unpackInt())
        assertEquals(1, Unpacker(Source("d2-00-00-00-01".dehex())).unpackInt())
        assertEquals(1, Unpacker(Source("d3-00-00-00-00-00-00-00-01".dehex())).unpackLong())
        assertEquals(1.0, Unpacker(Source("ca-3f-80-00-00".dehex())).unpackFloat()!!.toDouble(), 0.01)
        assertEquals(1.0, Unpacker(Source("cb-3f-f0-00-00-00-00-00-00".dehex())).unpackDouble()!!, 0.01)

        //    {
        //      "number": 127,
        //      "msgpack": [
        //        "7f",
        //        "cc-7f",
        //        "cd-00-7f",
        //        "ce-00-00-00-7f",
        //        "cf-00-00-00-00-00-00-00-7f",
        //        "d0-7f",
        //        "d1-00-7f",
        //        "d2-00-00-00-7f",
        //        "d3-00-00-00-00-00-00-00-7f"
        //      ]
        //    },

        assertEquals(127, Unpacker(Source("7f".dehex())).unpackInt())
        assertEquals(127, Unpacker(Source("cc-7f".dehex())).unpackInt())
        assertEquals(127, Unpacker(Source("cd-00-7f".dehex())).unpackInt())
        assertEquals(127, Unpacker(Source("ce-00-00-00-7f".dehex())).unpackInt())
        assertEquals(127, Unpacker(Source("ce-00-00-00-7f".dehex())).unpackLong())
        assertEquals(127uL, Unpacker(Source("cf-00-00-00-00-00-00-00-7f".dehex())).unpackULong())
        assertEquals(127, Unpacker(Source("d0-7f".dehex())).unpackInt())
        assertEquals(127, Unpacker(Source("d1-00-7f".dehex())).unpackInt())
        assertEquals(127, Unpacker(Source("d2-00-00-00-7f".dehex())).unpackInt())
        assertEquals(127, Unpacker(Source("d3-00-00-00-00-00-00-00-7f".dehex())).unpackLong())


        //    {
        //      "number": 128,
        //      "msgpack": [
        //        "cc-80",
        //        "cd-00-80",
        //        "ce-00-00-00-80",
        //        "cf-00-00-00-00-00-00-00-80",
        //        "d1-00-80",
        //        "d2-00-00-00-80",
        //        "d3-00-00-00-00-00-00-00-80"
        //      ]
        //    },

        assertEquals(128, Unpacker(Source("cc-80".dehex())).unpackInt())
        assertEquals(128, Unpacker(Source("cd-00-80".dehex())).unpackInt())
        assertEquals(128, Unpacker(Source("ce-00-00-00-80".dehex())).unpackInt())
        assertEquals(128, Unpacker(Source("ce-00-00-00-80".dehex())).unpackLong())
        assertEquals(128uL, Unpacker(Source("cf-00-00-00-00-00-00-00-80".dehex())).unpackULong())
        assertEquals(128, Unpacker(Source("d1-00-80".dehex())).unpackInt())
        assertEquals(128, Unpacker(Source("d2-00-00-00-80".dehex())).unpackInt())
        assertEquals(128, Unpacker(Source("d3-00-00-00-00-00-00-00-80".dehex())).unpackLong())

        //    {
        //      "number": 255,
        //      "msgpack": [
        //        "cc-ff",
        //        "cd-00-ff",
        //        "ce-00-00-00-ff",
        //        "cf-00-00-00-00-00-00-00-ff",
        //        "d1-00-ff",
        //        "d2-00-00-00-ff",
        //        "d3-00-00-00-00-00-00-00-ff"
        //      ]
        //    },

        assertEquals(255, Unpacker(Source("cc-ff".dehex())).unpackInt())
        assertEquals(255, Unpacker(Source("cd-00-ff".dehex())).unpackInt())
        assertEquals(255, Unpacker(Source("ce-00-00-00-ff".dehex())).unpackInt())
        assertEquals(255, Unpacker(Source("ce-00-00-00-ff".dehex())).unpackLong())
        assertEquals(255uL, Unpacker(Source("cf-00-00-00-00-00-00-00-ff".dehex())).unpackULong())
        assertEquals(255, Unpacker(Source("d1-00-ff".dehex())).unpackInt())
        assertEquals(255, Unpacker(Source("d2-00-00-00-ff".dehex())).unpackInt())
        assertEquals(255, Unpacker(Source("d3-00-00-00-00-00-00-00-ff".dehex())).unpackLong())

        //    {
        //      "number": 256,
        //      "msgpack": [
        //        "cd-01-00",
        //        "ce-00-00-01-00",
        //        "cf-00-00-00-00-00-00-01-00",
        //        "d1-01-00",
        //        "d2-00-00-01-00",
        //        "d3-00-00-00-00-00-00-01-00"
        //      ]
        //    },

        assertEquals(256, Unpacker(Source("cd-01-00".dehex())).unpackInt())
        assertEquals(256, Unpacker(Source("ce-00-00-01-00".dehex())).unpackInt())
        assertEquals(256, Unpacker(Source("ce-00-00-01-00".dehex())).unpackLong())
        assertEquals(256uL, Unpacker(Source("cf-00-00-00-00-00-00-01-00".dehex())).unpackULong())
        assertEquals(256, Unpacker(Source("d1-01-00".dehex())).unpackInt())
        assertEquals(256, Unpacker(Source("d2-00-00-01-00".dehex())).unpackInt())
        assertEquals(256, Unpacker(Source("d3-00-00-00-00-00-00-01-00".dehex())).unpackLong())

        //    {
        //      "number": 65535,
        //      "msgpack": [
        //        "cd-ff-ff",
        //        "ce-00-00-ff-ff",
        //        "cf-00-00-00-00-00-00-ff-ff",
        //        "d2-00-00-ff-ff",
        //        "d3-00-00-00-00-00-00-ff-ff"
        //      ]
        //    },

        assertEquals(65535, Unpacker(Source("cd-ff-ff".dehex())).unpackInt())
        assertEquals(65535, Unpacker(Source("ce-00-00-ff-ff".dehex())).unpackInt())
        assertEquals(65535, Unpacker(Source("ce-00-00-ff-ff".dehex())).unpackLong())
        assertEquals(65535uL, Unpacker(Source("cf-00-00-00-00-00-00-ff-ff".dehex())).unpackULong())
        assertEquals(65535, Unpacker(Source("d2-00-00-ff-ff".dehex())).unpackInt())
        assertEquals(65535, Unpacker(Source("d3-00-00-00-00-00-00-ff-ff".dehex())).unpackLong())

        //    {
        //      "number": 65536,
        //      "msgpack": [
        //        "ce-00-01-00-00",
        //        "cf-00-00-00-00-00-01-00-00",
        //        "d2-00-01-00-00",
        //        "d3-00-00-00-00-00-01-00-00"
        //      ]
        //    },

        assertEquals(65536, Unpacker(Source("ce-00-01-00-00".dehex())).unpackInt())
        assertEquals(65536, Unpacker(Source("ce-00-01-00-00".dehex())).unpackLong())
        assertEquals(65536uL, Unpacker(Source("cf-00-00-00-00-00-01-00-00".dehex())).unpackULong())
        assertEquals(65536, Unpacker(Source("d2-00-01-00-00".dehex())).unpackInt())
        assertEquals(65536, Unpacker(Source("d3-00-00-00-00-00-01-00-00".dehex())).unpackLong())

        //    {
        //      "number": 2147483647,
        //      "msgpack": [
        //        "ce-7f-ff-ff-ff",
        //        "cf-00-00-00-00-7f-ff-ff-ff",
        //        "d2-7f-ff-ff-ff",
        //        "d3-00-00-00-00-7f-ff-ff-ff"
        //      ]
        //    },

        assertEquals(2147483647, Unpacker(Source("ce-7f-ff-ff-ff".dehex())).unpackInt())
        assertEquals(2147483647, Unpacker(Source("ce-7f-ff-ff-ff".dehex())).unpackLong())
        assertEquals(2147483647uL, Unpacker(Source("cf-00-00-00-00-7f-ff-ff-ff".dehex())).unpackULong())
        assertEquals(2147483647, Unpacker(Source("d2-7f-ff-ff-ff".dehex())).unpackInt())
        assertEquals(2147483647, Unpacker(Source("d3-00-00-00-00-7f-ff-ff-ff".dehex())).unpackLong())

        //    {
        //      "number": 2147483648,
        //      "msgpack": [
        //        "ce-80-00-00-00",
        //        "cf-00-00-00-00-80-00-00-00",
        //        "d3-00-00-00-00-80-00-00-00",
        //        "ca-4f-00-00-00",
        //        "cb-41-e0-00-00-00-00-00-00"
        //      ]
        //    },

        assertThrows(Exception::class.java) { Unpacker(Source("ce-80-00-00-0".dehex())).unpackInt() }
        assertEquals(2147483648, Unpacker(Source("ce-80-00-00-00".dehex())).unpackLong())
        assertEquals(2147483648uL, Unpacker(Source("cf-00-00-00-00-80-00-00-00".dehex())).unpackULong())
        assertEquals(2147483648, Unpacker(Source("d3-00-00-00-00-80-00-00-00".dehex())).unpackLong())
        assertEquals(
            2147483648.0,
            Unpacker(Source("ca-4f-00-00-00".dehex())).unpackFloat()!!.toDouble(),
            0.01
        )
        assertEquals(
            2147483648.0,
            Unpacker(Source("cb-41-e0-00-00-00-00-00-00".dehex())).unpackDouble()!!,
            0.01
        )

        //    {
        //      "number": 4294967295,
        //      "msgpack": [
        //        "ce-ff-ff-ff-ff",
        //        "cf-00-00-00-00-ff-ff-ff-ff",
        //        "d3-00-00-00-00-ff-ff-ff-ff",
        //        "cb-41-ef-ff-ff-ff-e0-00-00"
        //      ]
        //    }
        //  ],

        assertThrows(Exception::class.java) { Unpacker(Source("ce-ff-ff-ff-ff".dehex())).unpackInt() }
        assertEquals(4294967295, Unpacker(Source("ce-ff-ff-ff-ff".dehex())).unpackLong())
        assertEquals(4294967295uL, Unpacker(Source("cf-00-00-00-00-ff-ff-ff-ff".dehex())).unpackULong())
        assertEquals(4294967295, Unpacker(Source("d3-00-00-00-00-ff-ff-ff-ff".dehex())).unpackLong())
        assertEquals(
            4294967295.0,
            Unpacker(Source("cb-41-ef-ff-ff-ff-e0-00-00".dehex())).unpackDouble()!!,
            0.01
        )

    }


}