package org.dedee.kompack.testsuite.comparision

import kotlinx.serialization.Serializable

@Serializable
data class Book(
    val author: String,
    val isbn: Long,
    val chapters: List<Chapter>
)

@Serializable
data class Chapter(
    val no: Int,
    val title: String,
    val paragraphs: List<String>,
)

class BookUtil {
    companion object {
        var chapterNumber = 0

        fun createBook(): Book {
            val chapters = mutableListOf<Chapter>()
            repeat(20) {
                chapters.add(createChapter())
            }
            return Book("author", 412432432443432, chapters)
        }

        fun createChapter(): Chapter {
            val paragraphs = mutableListOf<String>()
            repeat(50) {
                paragraphs.add(createParagraph())
            }
            return Chapter(chapterNumber++, "chapter123", paragraphs)
        }

        private fun createParagraph(): String {
            val p = mutableListOf<String>()
            repeat(100) {
                p.add(words.random())
            }
            return p.joinToString(" ")
        }


        val words = listOf(
            "switch",
            "harsh",
            "drink",
            "price",
            "hesitate",
            "comprehensive",
            "mercy",
            "trial",
            "established",
            "wound",
            "hospital",
            "coincide",
            "orbit",
            "sickness",
            "inspiration",
            "economic",
            "develop",
            "glass",
            "gradient",
            "horn",
            "problem",
            "deficit",
            "dollar",
            "disappoint",
            "decorative",
            "literacy",
            "discuss",
            "kitchen",
            "rung",
            "courtesy",
            "carve",
            "air",
            "critic",
            "warm",
            "aspect",
            "TRUE",
            "Sunday",
            "piano",
            "excavation",
            "fitness",
            "swarm",
            "doubt",
            "single",
            "swim",
            "number",
            "rotten",
            "regular",
            "ditch",
            "tail",
            "trend",
        )
    }
}