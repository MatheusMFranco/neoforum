package br.com.magalzim.neoforum.model

import br.com.magalzim.neoforum.form.UpdateTopicFormTest

object TopicTest {
    fun build() = Topic(
        id = 1,
        title = "Vamos estourar!",
        message = "Será que este tópico vai explodir?",
        board = BoardTest.build(),
        author = AuthorTest.build()
    )
    fun updated() = Topic(
        id = 1,
        title = UpdateTopicFormTest.build().title.toString(),
        message = UpdateTopicFormTest.build().message.toString(),
        board = BoardTest.build(),
        author = AuthorTest.build()
    )
}
