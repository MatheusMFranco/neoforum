package br.com.magalzim.neoforum.form

import br.com.magalzim.neoforum.model.AuthorTest
import br.com.magalzim.neoforum.model.TopicTest
import br.com.magalzim.neoforum.view.AnswerView
import java.time.LocalDateTime

object NewAnswerFormTest {
    fun build() = NewAnswerForm(
        message = "Será que este tópico vai explodir?",
        authorId = AuthorTest.build().id,
        topicId = TopicTest.build().id
    )
}