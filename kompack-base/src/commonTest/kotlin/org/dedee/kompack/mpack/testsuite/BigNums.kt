package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import kotlin.test.Test
import kotlin.test.assertEquals

class BigNums {

    @Test
    fun `23 number-bignum`() {
        // "23.number-bignum.yaml": [
        //    {
        //      "number": 4294967296,
        //      "bignum": "4294967296",
        //      "msgpack": [
        //        "cf-00-00-00-01-00-00-00-00",
        //        "d3-00-00-00-01-00-00-00-00",
        //        "ca-4f-80-00-00",
        //        "cb-41-f0-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(4294967296uL, InMemoryUnpacker("cf-00-00-00-01-00-00-00-00".dehex()).unpackULong())
        assertEquals(4294967296, InMemoryUnpacker("d3-00-00-00-01-00-00-00-00".dehex()).unpackLong())
        assertEquals(
            4294967296.0,
            InMemoryUnpacker("ca-4f-80-00-00".dehex()).unpackFloat()!!.toDouble(),
            0.01
        )
        assertEquals(
            4294967296.0,
            InMemoryUnpacker("cb-41-f0-00-00-00-00-00-00".dehex()).unpackDouble()!!,
            0.01
        )

        //    {
        //      "number": -4294967296,
        //      "bignum": "-4294967296",
        //      "msgpack": [
        //        "d3-ff-ff-ff-ff-00-00-00-00",
        //        "cb-c1-f0-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(-4294967296, InMemoryUnpacker("d3-ff-ff-ff-ff-00-00-00-00".dehex()).unpackLong())
        assertEquals(
            -4294967296.0,
            InMemoryUnpacker("cb-c1-f0-00-00-00-00-00-00".dehex()).unpackDouble()!!,
            0.01
        )

        //    {
        //      "number": 281474976710656,
        //      "bignum": "281474976710656",
        //      "msgpack": [
        //        "cf-00-01-00-00-00-00-00-00",
        //        "d3-00-01-00-00-00-00-00-00",
        //        "ca-57-80-00-00",
        //        "cb-42-f0-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(281474976710656uL, InMemoryUnpacker("cf-00-01-00-00-00-00-00-00".dehex()).unpackULong())
        assertEquals(281474976710656, InMemoryUnpacker("d3-00-01-00-00-00-00-00-00".dehex()).unpackLong())
        assertEquals(
            281474976710656.0,
            InMemoryUnpacker("ca-57-80-00-00".dehex()).unpackFloat()!!.toDouble(),
            0.01
        )
        assertEquals(
            281474976710656.0,
            InMemoryUnpacker("cb-42-f0-00-00-00-00-00-00".dehex()).unpackDouble()!!,
            0.01
        )

        //    {
        //      "number": -281474976710656,
        //      "bignum": "-281474976710656",
        //      "msgpack": [
        //        "d3-ff-ff-00-00-00-00-00-00",
        //        "ca-d7-80-00-00",
        //        "cb-c2-f0-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(-281474976710656, InMemoryUnpacker("d3-ff-ff-00-00-00-00-00-00".dehex()).unpackLong())
        assertEquals(
            -281474976710656.0,
            InMemoryUnpacker("ca-d7-80-00-00".dehex()).unpackFloat()!!.toDouble(),
            0.01
        )
        assertEquals(
            -281474976710656.0,
            InMemoryUnpacker("cb-c2-f0-00-00-00-00-00-00".dehex()).unpackDouble()!!,
            0.01
        )

        //    {
        //      "bignum": "9223372036854775807",
        //      "msgpack": [
        //        "d3-7f-ff-ff-ff-ff-ff-ff-ff",
        //        "cf-7f-ff-ff-ff-ff-ff-ff-ff"
        //      ]
        //    },

        assertEquals(
            9223372036854775807,
            InMemoryUnpacker("d3-7f-ff-ff-ff-ff-ff-ff-ff".dehex()).unpackLong()
        )
        assertEquals(
            9223372036854775807uL,
            InMemoryUnpacker("cf-7f-ff-ff-ff-ff-ff-ff-ff".dehex()).unpackULong()
        )

        //    {
        //      "bignum": "-9223372036854775807",
        //      "msgpack": [
        //        "d3-80-00-00-00-00-00-00-01"
        //      ]
        //    },

        assertEquals(
            -9223372036854775807,
            InMemoryUnpacker("d3-80-00-00-00-00-00-00-01".dehex()).unpackLong()
        )


        //    {
        //      "bignum": "9223372036854775808",
        //      "msgpack": [
        //        "cf-80-00-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(
            9223372036854775808uL,
            InMemoryUnpacker("cf-80-00-00-00-00-00-00-00".dehex()).unpackULong()
        )

        //    {
        //      "bignum": "-9223372036854775808",
        //      "msgpack": [
        //        "d3-80-00-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(
            "-9223372036854775808",
            InMemoryUnpacker("d3-80-00-00-00-00-00-00-00".dehex()).unpackLong().toString()
        )
        assertEquals(
            "-9223372036854775808".toLong(),
            InMemoryUnpacker("d3-80-00-00-00-00-00-00-00".dehex()).unpackLong()
        )

        //    {
        //      "bignum": "18446744073709551615",
        //      "msgpack": [
        //        "cf-ff-ff-ff-ff-ff-ff-ff-ff"
        //      ]
        //    }
        //  ],

        assertEquals(
            18446744073709551615uL,
            InMemoryUnpacker("cf-ff-ff-ff-ff-ff-ff-ff-ff".dehex()).unpackULong()
        )
    }
}