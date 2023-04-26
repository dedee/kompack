package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.InMemoryUnpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Floats {


    @Test
    fun `22 number float`() {
        //   "22.number-float.yaml": [
        //    {
        //      "number": 0.5,
        //      "msgpack": [
        //        "ca-3f-00-00-00",
        //        "cb-3f-e0-00-00-00-00-00-00"
        //      ]
        //    },

        assertEquals(0.5, InMemoryUnpacker("ca-3f-00-00-00".dehex()).unpackFloat()!!.toDouble(), 0.01)
        assertEquals(0.5, InMemoryUnpacker("cb-3f-e0-00-00-00-00-00-00".dehex()).unpackDouble()!!, 0.01)


        //    {
        //      "number": -0.5,
        //      "msgpack": [
        //        "ca-bf-00-00-00",
        //        "cb-bf-e0-00-00-00-00-00-00"
        //      ]
        //    }
        //  ],

        assertEquals(-0.5, InMemoryUnpacker("ca-bf-00-00-00".dehex()).unpackFloat()!!.toDouble(), 0.01)
        assertEquals(-0.5, InMemoryUnpacker("cb-bf-e0-00-00-00-00-00-00".dehex()).unpackDouble()!!, 0.01)

    }


}