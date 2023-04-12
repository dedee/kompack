package org.dedee.kompack.mpack.testsuite

import org.dedee.kompack.mpack.unpack.Source
import org.dedee.kompack.mpack.unpack.Unpacker
import org.dedee.kompack.mpack.util.dehex
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Strings {

    @Test
    fun `30 string ascii`() {
        //  "30.string-ascii.yaml": [
        //    {
        //      "string": "",
        //      "msgpack": [
        //        "a0",
        //        "d9-00",
        //        "da-00-00",
        //        "db-00-00-00-00"
        //      ]
        //    },

        assertEquals("", Unpacker(Source("a0".dehex())).unpackString())
        assertEquals("", Unpacker(Source("d9-00".dehex())).unpackString())
        assertEquals("", Unpacker(Source("da-00-00".dehex())).unpackString())
        assertEquals("", Unpacker(Source("db-00-00-00-00".dehex())).unpackString())

        //    {
        //      "string": "a",
        //      "msgpack": [
        //        "a1-61",
        //        "d9-01-61",
        //        "da-00-01-61",
        //        "db-00-00-00-01-61"
        //      ]
        //    },

        assertEquals("a", Unpacker(Source("a1-61".dehex())).unpackString())
        assertEquals("a", Unpacker(Source("d9-01-61".dehex())).unpackString())
        assertEquals("a", Unpacker(Source("da-00-01-61".dehex())).unpackString())
        assertEquals("a", Unpacker(Source("db-00-00-00-01-61".dehex())).unpackString())

        //    {
        //      "string": "1234567890123456789012345678901",
        //      "msgpack": [
        //        "bf-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31",
        //        "d9-1f-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31",
        //        "da-00-1f-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31"
        //      ]
        //    },

        assertEquals(
            "1234567890123456789012345678901",
            Unpacker(Source("bf-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31".dehex())).unpackString()
        )
        assertEquals(
            "1234567890123456789012345678901",
            Unpacker(Source("d9-1f-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31".dehex())).unpackString()
        )
        assertEquals(
            "1234567890123456789012345678901",
            Unpacker(Source("da-00-1f-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31".dehex())).unpackString()
        )


        //    {
        //      "string": "12345678901234567890123456789012",
        //      "msgpack": [
        //        "d9-20-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32",
        //        "da-00-20-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32"
        //      ]
        //    }
        //  ],

        assertEquals(
            "12345678901234567890123456789012",
            Unpacker(Source("d9-20-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32".dehex())).unpackString()
        )
        assertEquals(
            "12345678901234567890123456789012",
            Unpacker(Source("da-00-20-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32-33-34-35-36-37-38-39-30-31-32".dehex())).unpackString()
        )

    }

    @Test
    fun `31 string uml`() {
        //  "31.string-utf8.yaml": [
        //    {
        //      "string": "Кириллица",
        //      "msgpack": [
        //        "b2-d0-9a-d0-b8-d1-80-d0-b8-d0-bb-d0-bb-d0-b8-d1-86-d0-b0",
        //        "d9-12-d0-9a-d0-b8-d1-80-d0-b8-d0-bb-d0-bb-d0-b8-d1-86-d0-b0"
        //      ]
        //    },

        assertEquals(
            "Кириллица",
            Unpacker(Source("b2-d0-9a-d0-b8-d1-80-d0-b8-d0-bb-d0-bb-d0-b8-d1-86-d0-b0".dehex())).unpackString()
        )
        assertEquals(
            "Кириллица",
            Unpacker(Source("d9-12-d0-9a-d0-b8-d1-80-d0-b8-d0-bb-d0-bb-d0-b8-d1-86-d0-b0".dehex())).unpackString()
        )

        //    {
        //      "string": "ひらがな",
        //      "msgpack": [
        //        "ac-e3-81-b2-e3-82-89-e3-81-8c-e3-81-aa",
        //        "d9-0c-e3-81-b2-e3-82-89-e3-81-8c-e3-81-aa"
        //      ]
        //    },

        assertEquals(
            "ひらがな",
            Unpacker(Source("ac-e3-81-b2-e3-82-89-e3-81-8c-e3-81-aa".dehex())).unpackString()
        )
        assertEquals(
            "ひらがな",
            Unpacker(Source("d9-0c-e3-81-b2-e3-82-89-e3-81-8c-e3-81-aa".dehex())).unpackString()
        )


        //    {
        //      "string": "한글",
        //      "msgpack": [
        //        "a6-ed-95-9c-ea-b8-80",
        //        "d9-06-ed-95-9c-ea-b8-80"
        //      ]
        //    },

        assertEquals(
            "한글",
            Unpacker(Source("a6-ed-95-9c-ea-b8-80".dehex())).unpackString()
        )
        assertEquals(
            "한글",
            Unpacker(Source("d9-06-ed-95-9c-ea-b8-80".dehex())).unpackString()
        )

        //    {
        //      "string": "汉字",
        //      "msgpack": [
        //        "a6-e6-b1-89-e5-ad-97",
        //        "d9-06-e6-b1-89-e5-ad-97"
        //      ]
        //    },

        assertEquals(
            "汉字",
            Unpacker(Source("a6-e6-b1-89-e5-ad-97".dehex())).unpackString()
        )
        assertEquals(
            "汉字",
            Unpacker(Source("d9-06-e6-b1-89-e5-ad-97".dehex())).unpackString()
        )

        //    {
        //      "string": "漢字",
        //      "msgpack": [
        //        "a6-e6-bc-a2-e5-ad-97",
        //        "d9-06-e6-bc-a2-e5-ad-97"
        //      ]
        //    }
        //  ],

        assertEquals(
            "漢字",
            Unpacker(Source("a6-e6-bc-a2-e5-ad-97".dehex())).unpackString()
        )
        assertEquals(
            "漢字",
            Unpacker(Source("d9-06-e6-bc-a2-e5-ad-97".dehex())).unpackString()
        )
    }

    @Test
    fun `32 string emoji`() {
        //  "32.string-emoji.yaml": [
        //    {
        //      "string": "❤",
        //      "msgpack": [
        //        "a3-e2-9d-a4",
        //        "d9-03-e2-9d-a4"
        //      ]
        //    },

        assertEquals(
            "❤",
            Unpacker(Source("a3-e2-9d-a4".dehex())).unpackString()
        )
        assertEquals(
            "❤",
            Unpacker(Source("d9-03-e2-9d-a4".dehex())).unpackString()
        )

        //    {
        //      "string": "🍺",
        //      "msgpack": [
        //        "a4-f0-9f-8d-ba",
        //        "d9-04-f0-9f-8d-ba"
        //      ]
        //    }
        //  ],

        assertEquals(
            "\uD83C\uDF7A",
            Unpacker(Source("a4-f0-9f-8d-ba".dehex())).unpackString()
        )
        assertEquals(
            "\uD83C\uDF7A",
            Unpacker(Source("d9-04-f0-9f-8d-ba7".dehex())).unpackString()
        )
    }
}