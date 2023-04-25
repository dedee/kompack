# kompack

MessagePack serialization engine for Kotlin.

Note: Under construction!

[<img src="https://github.com/dedee/kompack/actions/workflows/build-gradle-project.yml/badge.svg">](<https://github.com/dedee/kompack/actions>)

## Goals

- Provide Kotlin implementation of http://msgpack.org specification
- Provide kotlinx.serialization engine using MessagePack format
- Support Kotlin multiplatform and Native (beside JVM)
- Minimal dependencies to other libraries
- High performance, low memory footprint
- Good test coverage
- Sonar code analytics
- Covers msgpack cross compatibility test
  suite https://github.com/kawanet/msgpack-test-suite/blob/master/dist/msgpack-test-suite.json

## Sonar

https://sonarcloud.io/project/overview?id=dedee_kompack

## Open

- Clarify pack/unpack UInt32 handling
- Array/Map type improvements Kotlin generics / reified?
- Full serialization features
- Large array support. Different stream based API required
- Exception concept in base
- BigNums not yet implemented - uint64... long
- Gradle build improvements
- CDB impl