package com.wunderbee.kompack.mpack.pack

import com.wunderbee.kompack.mpack.util.hex
import java.io.BufferedOutputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.system.measureTimeMillis
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SinkStreamTest {

    val out = ByteArrayOutputStream()

    @Test
    fun `Byte 00`() {
        SinkStream(out).addByte(0x00)
        assertEquals("00", out.toByteArray().hex())
    }

    @Test
    fun `Byte ff`() {
        SinkStream(out).addByte(0xff)
        assertEquals("ff", out.toByteArray().hex())
    }

    @Test
    fun `Byte fff`() {
        SinkStream(out).addInt16(0xfff)
        assertEquals("0fff", out.toByteArray().hex())
    }

    @Test
    fun `Int ffff`() {
        SinkStream(out).addInt32(0xffff)
        assertEquals("0000ffff", out.toByteArray().hex())
    }

    @Test
    fun `Large file support`() {
        val f = File.createTempFile("test", ".bin")

        BufferedOutputStream(FileOutputStream(f)).use {
            val t = measureTimeMillis {
                val p = StreamPacker(it)
                repeat(10_000) {
                    p.pack("hello world")
                }
            }
            it.flush()
            println("Written ${f.length()} in $t milliseconds")

            assertEquals(120000, f.length())
        }

        f.delete()
    }

    @Test
    fun `Really large file (120MB) support`() {
        val f = File.createTempFile("test", ".bin")
        try {
            BufferedOutputStream(FileOutputStream(f)).use {
                val t = measureTimeMillis {
                    val p = StreamPacker(it)
                    repeat(10_000_000) {
                        p.pack("hello world")
                    }
                }
                it.flush()
                println("Written ${f.length()} in $t milliseconds")
                assertEquals(120_000_000, f.length())

                // TODO read stream source
            }

        } finally {
            assertTrue(f.delete(), "Could not delete temp file")
        }
    }

}