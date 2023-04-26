package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class PositiveNumbers {

    @Test
    fun `20 number positive`() {
        assertNull(InMemoryUnpacker("c0".dehex()).unpackInt())
        assertNull(InMemoryUnpacker("c0".dehex()).unpackLong())

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
        assertEquals(0, InMemoryUnpacker("00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("cc-00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("cd-00-00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("ce-00-00-00-00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("ce-00-00-00-00".dehex()).unpackLong())
        assertEquals(0uL, InMemoryUnpacker("cf-00-00-00-00-00-00-00-00".dehex()).unpackULong())
        assertEquals(0, InMemoryUnpacker("d0-00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("d0-00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("d1-00-00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("d2-00-00-00-00".dehex()).unpackInt())
        assertEquals(0, InMemoryUnpacker("d3-00-00-00-00-00-00-00-00".dehex()).unpackLong())
        assertEquals(0.0, InMemoryUnpacker("ca-00-00-00-00".dehex()).unpackFloat()!!.toDouble(), 0.001)
        assertEquals(0.0, InMemoryUnpacker("cb-00-00-00-00-00-00-00-00".dehex()).unpackDouble()!!, 0.001)

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

        assertEquals(1, InMemoryUnpacker("01".dehex()).unpackInt())
        assertEquals(1, InMemoryUnpacker("cc-01".dehex()).unpackInt())
        assertEquals(1, InMemoryUnpacker("cd-00-01".dehex()).unpackInt())
        assertEquals(1, InMemoryUnpacker("ce-00-00-00-01".dehex()).unpackInt())
        assertEquals(1, InMemoryUnpacker("ce-00-00-00-01".dehex()).unpackLong())
        assertEquals(1uL, InMemoryUnpacker("cf-00-00-00-00-00-00-00-01".dehex()).unpackULong())
        assertEquals(1, InMemoryUnpacker("d0-01".dehex()).unpackInt())

        assertEquals(1, InMemoryUnpacker("d0-01".dehex()).unpackInt())
        assertEquals(1, InMemoryUnpacker("d1-00-01".dehex()).unpackInt())
        assertEquals(1, InMemoryUnpacker("d2-00-00-00-01".dehex()).unpackInt())
        assertEquals(1, InMemoryUnpacker("d3-00-00-00-00-00-00-00-01".dehex()).unpackLong())
        assertEquals(1.0, InMemoryUnpacker("ca-3f-80-00-00".dehex()).unpackFloat()!!.toDouble(), 0.01)
        assertEquals(1.0, InMemoryUnpacker("cb-3f-f0-00-00-00-00-00-00".dehex()).unpackDouble()!!, 0.01)

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

        assertEquals(127, InMemoryUnpacker("7f".dehex()).unpackInt())
        assertEquals(127, InMemoryUnpacker("cc-7f".dehex()).unpackInt())
        assertEquals(127, InMemoryUnpacker("cd-00-7f".dehex()).unpackInt())
        assertEquals(127, InMemoryUnpacker("ce-00-00-00-7f".dehex()).unpackInt())
        assertEquals(127, InMemoryUnpacker("ce-00-00-00-7f".dehex()).unpackLong())
        assertEquals(127uL, InMemoryUnpacker("cf-00-00-00-00-00-00-00-7f".dehex()).unpackULong())
        assertEquals(127, InMemoryUnpacker("d0-7f".dehex()).unpackInt())
        assertEquals(127, InMemoryUnpacker("d1-00-7f".dehex()).unpackInt())
        assertEquals(127, InMemoryUnpacker("d2-00-00-00-7f".dehex()).unpackInt())
        assertEquals(127, InMemoryUnpacker("d3-00-00-00-00-00-00-00-7f".dehex()).unpackLong())


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

        assertEquals(128, InMemoryUnpacker("cc-80".dehex()).unpackInt())
        assertEquals(128, InMemoryUnpacker("cd-00-80".dehex()).unpackInt())
        assertEquals(128, InMemoryUnpacker("ce-00-00-00-80".dehex()).unpackInt())
        assertEquals(128, InMemoryUnpacker("ce-00-00-00-80".dehex()).unpackLong())
        assertEquals(128uL, InMemoryUnpacker("cf-00-00-00-00-00-00-00-80".dehex()).unpackULong())
        assertEquals(128, InMemoryUnpacker("d1-00-80".dehex()).unpackInt())
        assertEquals(128, InMemoryUnpacker("d2-00-00-00-80".dehex()).unpackInt())
        assertEquals(128, InMemoryUnpacker("d3-00-00-00-00-00-00-00-80".dehex()).unpackLong())

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

        assertEquals(255, InMemoryUnpacker("cc-ff".dehex()).unpackInt())
        assertEquals(255, InMemoryUnpacker("cd-00-ff".dehex()).unpackInt())
        assertEquals(255, InMemoryUnpacker("ce-00-00-00-ff".dehex()).unpackInt())
        assertEquals(255, InMemoryUnpacker("ce-00-00-00-ff".dehex()).unpackLong())
        assertEquals(255uL, InMemoryUnpacker("cf-00-00-00-00-00-00-00-ff".dehex()).unpackULong())
        assertEquals(255, InMemoryUnpacker("d1-00-ff".dehex()).unpackInt())
        assertEquals(255, InMemoryUnpacker("d2-00-00-00-ff".dehex()).unpackInt())
        assertEquals(255, InMemoryUnpacker("d3-00-00-00-00-00-00-00-ff".dehex()).unpackLong())

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

        assertEquals(256, InMemoryUnpacker("cd-01-00".dehex()).unpackInt())
        assertEquals(256, InMemoryUnpacker("ce-00-00-01-00".dehex()).unpackInt())
        assertEquals(256, InMemoryUnpacker("ce-00-00-01-00".dehex()).unpackLong())
        assertEquals(256uL, InMemoryUnpacker("cf-00-00-00-00-00-00-01-00".dehex()).unpackULong())
        assertEquals(256, InMemoryUnpacker("d1-01-00".dehex()).unpackInt())
        assertEquals(256, InMemoryUnpacker("d2-00-00-01-00".dehex()).unpackInt())
        assertEquals(256, InMemoryUnpacker("d3-00-00-00-00-00-00-01-00".dehex()).unpackLong())

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

        assertEquals(65535, InMemoryUnpacker("cd-ff-ff".dehex()).unpackInt())
        assertEquals(65535, InMemoryUnpacker("ce-00-00-ff-ff".dehex()).unpackInt())
        assertEquals(65535, InMemoryUnpacker("ce-00-00-ff-ff".dehex()).unpackLong())
        assertEquals(65535uL, InMemoryUnpacker("cf-00-00-00-00-00-00-ff-ff".dehex()).unpackULong())
        assertEquals(65535, InMemoryUnpacker("d2-00-00-ff-ff".dehex()).unpackInt())
        assertEquals(65535, InMemoryUnpacker("d3-00-00-00-00-00-00-ff-ff".dehex()).unpackLong())

        //    {
        //      "number": 65536,
        //      "msgpack": [
        //        "ce-00-01-00-00",
        //        "cf-00-00-00-00-00-01-00-00",
        //        "d2-00-01-00-00",
        //        "d3-00-00-00-00-00-01-00-00"
        //      ]
        //    },

        assertEquals(65536, InMemoryUnpacker("ce-00-01-00-00".dehex()).unpackInt())
        assertEquals(65536, InMemoryUnpacker("ce-00-01-00-00".dehex()).unpackLong())
        assertEquals(65536uL, InMemoryUnpacker("cf-00-00-00-00-00-01-00-00".dehex()).unpackULong())
        assertEquals(65536, InMemoryUnpacker("d2-00-01-00-00".dehex()).unpackInt())
        assertEquals(65536, InMemoryUnpacker("d3-00-00-00-00-00-01-00-00".dehex()).unpackLong())

        //    {
        //      "number": 2147483647,
        //      "msgpack": [
        //        "ce-7f-ff-ff-ff",
        //        "cf-00-00-00-00-7f-ff-ff-ff",
        //        "d2-7f-ff-ff-ff",
        //        "d3-00-00-00-00-7f-ff-ff-ff"
        //      ]
        //    },

        assertEquals(2147483647, InMemoryUnpacker("ce-7f-ff-ff-ff".dehex()).unpackInt())
        assertEquals(2147483647, InMemoryUnpacker("ce-7f-ff-ff-ff".dehex()).unpackLong())
        assertEquals(2147483647uL, InMemoryUnpacker("cf-00-00-00-00-7f-ff-ff-ff".dehex()).unpackULong())
        assertEquals(2147483647, InMemoryUnpacker("d2-7f-ff-ff-ff".dehex()).unpackInt())
        assertEquals(2147483647, InMemoryUnpacker("d3-00-00-00-00-7f-ff-ff-ff".dehex()).unpackLong())

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

        assertThrows(Exception::class.java) { InMemoryUnpacker("ce-80-00-00-00".dehex()).unpackInt() }
        assertEquals(2147483648, InMemoryUnpacker("ce-80-00-00-00".dehex()).unpackLong())
        assertEquals(2147483648uL, InMemoryUnpacker("cf-00-00-00-00-80-00-00-00".dehex()).unpackULong())
        assertEquals(2147483648, InMemoryUnpacker("d3-00-00-00-00-80-00-00-00".dehex()).unpackLong())
        assertEquals(
            2147483648.0,
            InMemoryUnpacker("ca-4f-00-00-00".dehex()).unpackFloat()!!.toDouble(),
            0.01
        )
        assertEquals(
            2147483648.0,
            InMemoryUnpacker("cb-41-e0-00-00-00-00-00-00".dehex()).unpackDouble()!!,
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

        assertThrows(Exception::class.java) { InMemoryUnpacker("ce-ff-ff-ff-ff".dehex()).unpackInt() }
        assertEquals(4294967295, InMemoryUnpacker("ce-ff-ff-ff-ff".dehex()).unpackLong())
        assertEquals(4294967295uL, InMemoryUnpacker("cf-00-00-00-00-ff-ff-ff-ff".dehex()).unpackULong())
        assertEquals(4294967295, InMemoryUnpacker("d3-00-00-00-00-ff-ff-ff-ff".dehex()).unpackLong())
        assertEquals(
            4294967295.0,
            InMemoryUnpacker("cb-41-ef-ff-ff-ff-e0-00-00".dehex()).unpackDouble()!!,
            0.01
        )

    }


}