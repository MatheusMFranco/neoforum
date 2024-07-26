package br.com.magalzim.neoforum.view

import br.com.magalzim.neoforum.form.UpdateAnswerFormTest
import br.com.magalzim.neoforum.model.AuthorTest
import br.com.magalzim.neoforum.util.DateUtil

object AnswerViewTest {
    fun build() = AnswerView(
        id = 1,
        message = "Será que este tópico vai explodir?",
        registerDate = DateUtil.mock,
        author = AuthorTest.build().name,
    )

    fun updated() = AnswerView(
        id = 1,
        message = UpdateAnswerFormTest.build().message.toString(),
        registerDate = DateUtil.mock,
        author = AuthorTest.build().name,
    )
}