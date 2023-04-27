# kompack

MessagePack serialization engine for Kotlin.

Note: Under construction!

[<img src="https://github.com/dedee/kompack/actions/workflows/build-gradle-project.yml/badge.svg">](<https://github.com/dedee/kompack/actions>)

## Kotlinx Serialization API

In memory (byte array)

    val addressBook = AddressBook(
        listOf(
            Entry("Heinz A. Mueller", "State St. 8", 31232, "Kalamazoo"),
            Entry("Max Mustermann", "Schlossstr. 8", 78334, "Ludwigsburg"),
        )
    )

    val encodedByteArray = MessagePackEncoder.encodeToByteArray(addressBook)

Stream based

    BufferedOutputStream(FileOutputStream("test.dat")).let { out ->
        MessagePackEncoder.encodeToStream(addressBook, out)
    }

## Low level API

In memory (byte array)

    val byteArray = InMemoryPacker(100)
      .pack(true)
      .pack(10)
      .build()

    val p = InMemoryUnpacker(byteArray)
    val b = p.unpackBoolean() // true
    val i = p.unpackInt() // 10

Stream based

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

## Performance comparision

### Encoding speed using bytearray (in memory)

org.dedee.kompack.testsuite.comparision.Comparision

    Encoding speed 1000x book encoding
    ----------------------------------
    com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack:  6390 ms
    org.dedee.kompack.mpack.coders.MessagePackEncoder:        453 ms
    kotlinx.serialization.json.Json:                          2038 ms

    Decoding speed 1000x book decoding
    ----------------------------------
    com.ensarsarajcic.kotlinx.serialization.msgpack.MsgPack:  1886 ms
    org.dedee.kompack.mpack.coders.MessagePackEncoder:        663 ms
    kotlinx.serialization.json.Json:                          1038 ms

## Sonar

https://sonarcloud.io/project/overview?id=dedee_kompack

## Open

- Array/Map type improvements Kotlin generics / reified?
- Exception concept in base
- BigNums not yet implemented - uint64... long
- Gradle build improvements
- CDB impl