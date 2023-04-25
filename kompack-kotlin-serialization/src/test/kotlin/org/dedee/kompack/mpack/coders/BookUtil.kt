package org.dedee.kompack.mpack.coders

class BookUtil {
    companion object {
        fun createBook(): BookTests.Book {
            val chapters = mutableListOf<BookTests.Chapter>()
            repeat(20) {
                chapters.add(createChapter())
            }
            return BookTests.Book("author", "412432432443432", chapters)
        }

        fun createChapter(): BookTests.Chapter {
            val paragraphs = mutableListOf<String>()
            repeat(50) {
                paragraphs.add(createParagraph())
            }
            return BookTests.Chapter("chapter123", paragraphs)
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