# Kompack - Kotlin Multiplatform MessagePack Serialization Engine

Serialization engine for Kotlin Multiplatform (JVM/native) using MessagePack binary format
to optimize size and performance.

If you want to learn more about MessagePack, check this website: https://www.msgpack.org

[<img src="https://github.com/dedee/kompack/actions/workflows/build.yml/badge.svg">](<https://github.com/dedee/kompack/actions>)

**NOTE**
This library is development - use with care. I am looking forward to your feedback.
Current releases just contain JVM and mingw_x64 native libraries. More platforms will be built soon.

## Kotlinx Serialization API

Here is an example. Simple address book structure, using Kotlin data classes
with @Serializable annotation.

https://github.com/dedee/kompack-sample-application

### Add dependency

    dependencies {
      implementation group: 'com.wunderbee.kompack', name: 'kompack-kotlin-serialization', version: '0.0.2'
    }

### Use/Create your Kotlin data classes with kotlinx.serialization

    @Serializable
    data class Entry(val name: String, val street: String, val zip: Int, val city: String)

    @Serializable
    data class AddressBook(val entries: List<Entry>)

    val addressBook = AddressBook(
        listOf(
            Entry("Heinz A. Mueller", "State St. 8", 31232, "Kalamazoo"),
            Entry("Max Mustermann", "Schlossstr. 8", 78334, "Ludwigsburg"),
        )
    )

### Serialize them

In memory (byte array) you can simply serialize the address book into a ByteArray.

    val encodedByteArray = MessagePackEncoder.encodeToByteArray(addressBook)

You can also write it into a stream (e.g. file stream), which is perfect for large 
structures.

    BufferedOutputStream(FileOutputStream("test.dat")).use { out ->
        MessagePackEncoder.encodeToStream(addressBook, out)
    }

### Comparison to JSON

Using MessagePack may make sense to you, if size and encoding speed matters
In 'real world' scenarios, you can simply reduce the encoded size.

        val encodedByteArray = MessagePackEncoder.encodeToByteArray(addressBook)
        println("Encoded address book into ${encodedByteArray.size} bytes")
        // Encoded address book into 89 bytes

        val jsonString = Json.encodeToString(addressBook)
        println("Encoded address book into ${jsonString.length} chars")
        // Encoded address book into 179 chars

For sure, its binary and no more readable/editable. So it depends on your use case.

## Low level API

In kompack-base we provide a low level API to read and write MessagePack structures.
You can use that, if you do not like to use the kotlinx serialization framework.

You can serialize many different types directly into a byte array (in memory)

    val byteArray = InMemoryPacker(100)
      .pack(true)
      .pack(10)
      .build()

    val p = InMemoryUnpacker(byteArray)
    val b = p.unpackBoolean() // true
    val i = p.unpackInt() // 10

Or you can use the stream API to write larger files

    StreamUnpacker(BufferedInputStream(FileInputStream("in.dat"))).use { p ->
      val b = p.unpackBoolean() // true
      val i = p.unpackInt() // 10
    }

## Goals

- Provide Kotlin implementation of http://msgpack.org specification
- Provide kotlinx.serialization engine using MessagePack format
- Focus on performance and reduce memory consumption
- Support Kotlin multiplatform and Native (beside JVM)
- Minimal dependencies to other libraries
- High performance, low memory footprint
- Good test coverage
- Sonar code analytics
- Covers msgpack cross compatibility test
  suite https://github.com/kawanet/msgpack-test-suite/blob/master/dist/msgpack-test-suite.json

## Performance comparison

### Encoding speed using bytearray (in memory)

com.wunderbee.kompack.testsuite.comparision.Comparision

    Encoding speed 1000x book encoding
    ----------------------------------
    com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack:  6390 ms
    com.wunderbee.kompack.mpack.coders.MessagePackEncoder:     453 ms
    kotlinx.serialization.json.Json:                          2038 ms

    Decoding speed 1000x book decoding
    ----------------------------------
    com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack:  1886 ms
    com.wunderbee.kompack.mpack.coders.MessagePackEncoder:     663 ms
    kotlinx.serialization.json.Json:                          1038 ms

## Sonar

https://sonarcloud.io/project/overview?id=dedee_kompack

## Open

- Support more target platforms. Build, cross compilation TBD
- Array/Map type improvements Kotlin generics / reified?
- Exception concept in base
- BigNums not yet implemented - uint64... long
- Gradle build improvements
- CDB impl
