package org.dedee.kompack.mpack.util

import org.dedee.kompack.mpack.pack.Packer
import org.dedee.kompack.mpack.pack.Sink
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.msgpack.MessagePack
import java.io.ByteArrayOutputStream

class CompatibilityTest {

    fun `Compatibility Int 1`() {
        // positive fixint stores 7-bit positive integer
        //+--------+
        //|0XXXXXXX|
        //+--------+
        //
        //negative fixint stores 5-bit negative integer
        //+--------+
        //|111YYYYY|
        //+--------+
        //
        //* 0XXXXXXX is 8-bit unsigned integer
        //* 111YYYYY is 8-bit signed integer
        //
        //uint 8 stores a 8-bit unsigned integer
        //+--------+--------+
        //|  0xcc  |ZZZZZZZZ|
        //+--------+--------+
        //
        //uint 16 stores a 16-bit big-endian unsigned integer
        //+--------+--------+--------+
        //|  0xcd  |ZZZZZZZZ|ZZZZZZZZ|
        //+--------+--------+--------+
        //
        //uint 32 stores a 32-bit big-endian unsigned integer
        //+--------+--------+--------+--------+--------+
        //|  0xce  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        //+--------+--------+--------+--------+--------+
        //
        //uint 64 stores a 64-bit big-endian unsigned integer
        //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
        //|  0xcf  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
        //
        //int 8 stores a 8-bit signed integer
        //+--------+--------+
        //|  0xd0  |ZZZZZZZZ|
        //+--------+--------+
        //
        //int 16 stores a 16-bit big-endian signed integer
        //+--------+--------+--------+
        //|  0xd1  |ZZZZZZZZ|ZZZZZZZZ|
        //+--------+--------+--------+
        //
        //int 32 stores a 32-bit big-endian signed integer
        //+--------+--------+--------+--------+--------+
        //|  0xd2  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        //+--------+--------+--------+--------+--------+
        //
        //int 64 stores a 64-bit big-endian signed integer
        //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
        //|  0xd3  |ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|ZZZZZZZZ|
        //+--------+--------+--------+--------+--------+--------+--------+--------+--------+
    }

    @Test
    fun compat() {
        val out = ByteArrayOutputStream(10)
        val orgSerializer = MessagePack().createPacker(out)
        val mySerializer = Packer(Sink(ByteArray(10)))


//        orgSerializer.writeArrayBegin(2)
//        orgSerializer.write(0)
//        orgSerializer.write("hello")
//        orgSerializer.writeArrayEnd()
//
//        println(out.toByteArray().toHexString())

        val integerTests = arrayOf(1, 2, 0xff, 0x1ff, -0x7f, 65534)

        for (i in integerTests) {

            orgSerializer.write(i)
            val desiredValue = out.toByteArray().hex()
            out.reset()
            val actualValue = mySerializer.pack(i).build().hex()

            println("$i -> $desiredValue == $actualValue")

            assertEquals(desiredValue, actualValue)
        }
    }
}