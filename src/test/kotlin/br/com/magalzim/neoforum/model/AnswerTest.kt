package br.com.magalzim.neoforum.model

import br.com.magalzim.neoforum.form.UpdateAnswerFormTest
import br.com.magalzim.neoforum.util.DateUtil

object AnswerTest {
    private fun create(message: String) = Answer(
        id = 1,
        message = message,
        registerDate = DateUtil.mock,
        author = AuthorTest.build(),
        topic = TopicTest.build()
    )
    fun build() = create("Será que este tópico vai explodir?")
    fun updated() = create(UpdateAnswerFormTest.build().message.toString())
}
