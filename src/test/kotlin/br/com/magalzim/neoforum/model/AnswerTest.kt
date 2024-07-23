package br.com.magalzim.neoforum.model

import br.com.magalzim.neoforum.util.DateUtil

object AnswerTest {
    fun build() = Answer(
        id = 1,
        message = "Será que este tópico vai explodir?",
        registerDate = DateUtil.mock,
        author = AuthorTest.build(),
        topic = TopicTest.build()
    )
}
