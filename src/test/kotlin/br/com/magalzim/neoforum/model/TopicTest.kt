package br.com.magalzim.neoforum.model

object TopicTest {
    fun build() = Topic(
        id = 1,
        title = "Vamos estourar!",
        message = "Será que este tópico vai explodir?",
        board = BoardTest.build(),
        author = AuthorTest.build()
    )
}
