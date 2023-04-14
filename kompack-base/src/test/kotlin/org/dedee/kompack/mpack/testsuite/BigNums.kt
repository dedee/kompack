package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

        Assertions.assertEquals(4294967296uL, Unpacker(Source("cf-00-00-00-01-00-00-00-00".dehex())).unpackULong())
        Assertions.assertEquals(4294967296, Unpacker(Source("d3-00-00-00-01-00-00-00-00".dehex())).unpackLong())
        Assertions.assertEquals(
            4294967296.0,
            Unpacker(Source("ca-4f-80-00-00".dehex())).unpackFloat()!!.toDouble(),
            0.01
        )
        Assertions.assertEquals(
            4294967296.0,
            Unpacker(Source("cb-41-f0-00-00-00-00-00-00".dehex())).unpackDouble()!!,
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

        Assertions.assertEquals(-4294967296, Unpacker(Source("d3-ff-ff-ff-ff-00-00-00-00".dehex())).unpackLong())
        Assertions.assertEquals(
            -4294967296.0,
            Unpacker(Source("cb-c1-f0-00-00-00-00-00-00".dehex())).unpackDouble()!!,
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

        Assertions.assertEquals(281474976710656uL, Unpacker(Source("cf-00-01-00-00-00-00-00-00".dehex())).unpackULong())
        Assertions.assertEquals(281474976710656, Unpacker(Source("d3-00-01-00-00-00-00-00-00".dehex())).unpackLong())
        Assertions.assertEquals(
            281474976710656.0,
            Unpacker(Source("ca-57-80-00-00".dehex())).unpackFloat()!!.toDouble(),
            0.01
        )
        Assertions.assertEquals(
            281474976710656.0,
            Unpacker(Source("cb-42-f0-00-00-00-00-00-00".dehex())).unpackDouble()!!,
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

        Assertions.assertEquals(-281474976710656, Unpacker(Source("d3-ff-ff-00-00-00-00-00-00".dehex())).unpackLong())
        Assertions.assertEquals(
            -281474976710656.0,
            Unpacker(Source("ca-d7-80-00-00".dehex())).unpackFloat()!!.toDouble(),
            0.01
        )
        Assertions.assertEquals(
            -281474976710656.0,
            Unpacker(Source("cb-c2-f0-00-00-00-00-00-00".dehex())).unpackDouble()!!,
            0.01
        )

        //    {
        //      "bignum": "9223372036854775807",
        //      "msgpack": [
        //        "d3-7f-ff-ff-ff-ff-ff-ff-ff",
        //        "cf-7f-ff-ff-ff-ff-ff-ff-ff"
        //      ]
        //    },

        Assertions.assertEquals(
            9223372036854775807,
            Unpacker(Source("d3-7f-ff-ff-ff-ff-ff-ff-ff".dehex())).unpackLong()
        )
        Assertions.assertEquals(
            9223372036854775807uL,
            Unpacker(Source("cf-7f-ff-ff-ff-ff-ff-ff-ff".dehex())).unpackULong()
        )

        //    {
        //      "bignum": "-9223372036854775807",
        //      "msgpack": [
        //        "d3-80-00-00-00-00-00-00-01"
        //      ]
        //    },

        Assertions.assertEquals(
            -9223372036854775807,
            Unpacker(Source("d3-80-00-00-00-00-00-00-01".dehex())).unpackLong()
        )


        //    {
        //      "bignum": "9223372036854775808",
        //      "msgpack": [
        //        "cf-80-00-00-00-00-00-00-00"
        //      ]
        //    },

        Assertions.assertEquals(9223372036854775808uL, Unpacker(Source("cf-80-00-00-00-00-00-00-00".dehex())).unpackULong())

        //    {
        //      "bignum": "-9223372036854775808",
        //      "msgpack": [
        //        "d3-80-00-00-00-00-00-00-00"
        //      ]
        //    },

        Assertions.assertEquals(
            "-9223372036854775808",
            Unpacker(Source("d3-80-00-00-00-00-00-00-00".dehex())).unpackLong().toString()
        )
        Assertions.assertEquals(
            "-9223372036854775808".toLong(),
            Unpacker(Source("d3-80-00-00-00-00-00-00-00".dehex())).unpackLong()
        )

        //    {
        //      "bignum": "18446744073709551615",
        //      "msgpack": [
        //        "cf-ff-ff-ff-ff-ff-ff-ff-ff"
        //      ]
        //    }
        //  ],

        Assertions.assertEquals(
            18446744073709551615uL,
            Unpacker(Source("cf-ff-ff-ff-ff-ff-ff-ff-ff".dehex())).unpackULong()
        )
    }
}