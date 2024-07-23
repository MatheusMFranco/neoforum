package br.com.magalzim.neoforum.form

import br.com.magalzim.neoforum.model.AuthorTest
import br.com.magalzim.neoforum.model.TopicTest

object NewAnswerFormTest {
    fun build() = NewAnswerForm(
        message = "Será que este tópico vai explodir?",
        authorId = AuthorTest.build().id,
        topicId = TopicTest.build().id
    )
}