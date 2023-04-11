package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class NegativeNumbers {

    @Test
    fun `21 negative numbers`() {
        //  "21.number-negative.yaml": [
        //    {
        //      "number": -1,
        //      "msgpack": [
        //        "ff",
        //        "d0-ff",
        //        "d1-ff-ff",
        //        "d2-ff-ff-ff-ff",
        //        "d3-ff-ff-ff-ff-ff-ff-ff-ff",
        //        "ca-bf-80-00-00",
        //        "cb-bf-f0-00-00-00-00-00-00"
        //      ]
        //    },

        Assertions.assertEquals(-1, Unpacker(Source("ff".dehex())).unpackInt())
        Assertions.assertEquals(-1, Unpacker(Source("d0-ff".dehex())).unpackInt())
        Assertions.assertEquals(-1, Unpacker(Source("d1-ff-ff".dehex())).unpackInt())
        Assertions.assertEquals(-1, Unpacker(Source("d2-ff-ff-ff-ff".dehex())).unpackInt())
        Assertions.assertEquals(-1, Unpacker(Source("d3-ff-ff-ff-ff-ff-ff-ff-ff".dehex())).unpackLong())
        Assertions.assertEquals(-1.0, Unpacker(Source("ca-bf-80-00-00".dehex())).unpackFloat()!!.toDouble(), 0.01)
        Assertions.assertEquals(-1.0, Unpacker(Source("cb-bf-f0-00-00-00-00-00-00".dehex())).unpackDouble()!!, 0.01)

        //    {
        //      "number": -32,
        //      "msgpack": [
        //        "e0",
        //        "d0-e0",
        //        "d1-ff-e0",
        //        "d2-ff-ff-ff-e0",
        //        "d3-ff-ff-ff-ff-ff-ff-ff-e0",
        //        "ca-c2-00-00-00",
        //        "cb-c0-40-00-00-00-00-00-00"
        //      ]
        //    },

        Assertions.assertEquals(-32, Unpacker(Source("e0".dehex())).unpackInt())
        Assertions.assertEquals(-32, Unpacker(Source("d0-e0".dehex())).unpackInt())
        Assertions.assertEquals(-32, Unpacker(Source("d1-ff-e0".dehex())).unpackInt())
        Assertions.assertEquals(-32, Unpacker(Source("d2-ff-ff-ff-e0".dehex())).unpackInt())
        Assertions.assertEquals(-32, Unpacker(Source("d3-ff-ff-ff-ff-ff-ff-ff-e0".dehex())).unpackLong())
        Assertions.assertEquals(-32.0, Unpacker(Source("ca-c2-00-00-00".dehex())).unpackFloat()!!.toDouble(), 0.01)
        Assertions.assertEquals(-32.0, Unpacker(Source("cb-c0-40-00-00-00-00-00-00".dehex())).unpackDouble()!!, 0.01)

        //    {
        //      "number": -33,
        //      "msgpack": [
        //        "d0-df",
        //        "d1-ff-df",
        //        "d2-ff-ff-ff-df",
        //        "d3-ff-ff-ff-ff-ff-ff-ff-df"
        //      ]
        //    },

        Assertions.assertEquals(-33, Unpacker(Source("d0-df".dehex())).unpackInt())
        Assertions.assertEquals(-33, Unpacker(Source("d1-ff-df".dehex())).unpackInt())
        Assertions.assertEquals(-33, Unpacker(Source("d2-ff-ff-ff-df".dehex())).unpackInt())
        Assertions.assertEquals(-33, Unpacker(Source("d3-ff-ff-ff-ff-ff-ff-ff-df".dehex())).unpackLong())

        //    {
        //      "number": -128,
        //      "msgpack": [
        //        "d0-80",
        //        "d1-ff-80",
        //        "d2-ff-ff-ff-80",
        //        "d3-ff-ff-ff-ff-ff-ff-ff-80"
        //      ]
        //    },

        Assertions.assertEquals(-128, Unpacker(Source("d0-80".dehex())).unpackInt())
        Assertions.assertEquals(-128, Unpacker(Source("d1-ff-80".dehex())).unpackInt())
        Assertions.assertEquals(-128, Unpacker(Source("d2-ff-ff-ff-80".dehex())).unpackInt())
        Assertions.assertEquals(-128, Unpacker(Source("d3-ff-ff-ff-ff-ff-ff-ff-80".dehex())).unpackLong())


        //    {
        //      "number": -256,
        //      "msgpack": [
        //        "d1-ff-00",
        //        "d2-ff-ff-ff-00",
        //        "d3-ff-ff-ff-ff-ff-ff-ff-00"
        //      ]
        //    },

        Assertions.assertEquals(-256, Unpacker(Source("d1-ff-00".dehex())).unpackInt())
        Assertions.assertEquals(-256, Unpacker(Source("d2-ff-ff-ff-00".dehex())).unpackInt())
        Assertions.assertEquals(-256, Unpacker(Source("d3-ff-ff-ff-ff-ff-ff-ff-00".dehex())).unpackLong())

        //    {
        //      "number": -32768,
        //      "msgpack": [
        //        "d1-80-00",
        //        "d2-ff-ff-80-00",
        //        "d3-ff-ff-ff-ff-ff-ff-80-00"
        //      ]
        //    },

        Assertions.assertEquals(-32768, Unpacker(Source("d1-80-00".dehex())).unpackInt())
        Assertions.assertEquals(-32768, Unpacker(Source("d2-ff-ff-80-00".dehex())).unpackInt())
        Assertions.assertEquals(-32768, Unpacker(Source("d3-ff-ff-ff-ff-ff-ff-80-00".dehex())).unpackLong())

        //    {
        //      "number": -65536,
        //      "msgpack": [
        //        "d2-ff-ff-00-00",
        //        "d3-ff-ff-ff-ff-ff-ff-00-00"
        //      ]
        //    },

        Assertions.assertEquals(-65536, Unpacker(Source("d2-ff-ff-00-00".dehex())).unpackInt())
        Assertions.assertEquals(-65536, Unpacker(Source("d3-ff-ff-ff-ff-ff-ff-00-00".dehex())).unpackLong())

        //    {
        //      "number": -2147483648,
        //      "msgpack": [
        //        "d2-80-00-00-00",
        //        "d3-ff-ff-ff-ff-80-00-00-00",
        //        "cb-c1-e0-00-00-00-00-00-00"
        //      ]
        //    }
        //  ],

        Assertions.assertEquals(-2147483648, Unpacker(Source("d2-80-00-00-00".dehex())).unpackInt())
        Assertions.assertEquals(-2147483648, Unpacker(Source("d3-ff-ff-ff-ff-80-00-00-00".dehex())).unpackLong())
        Assertions.assertEquals(
            -2147483648.0,
            Unpacker(Source("cb-c1-e0-00-00-00-00-00-00".dehex())).unpackDouble()!!,
            0.01
        )

    }

}