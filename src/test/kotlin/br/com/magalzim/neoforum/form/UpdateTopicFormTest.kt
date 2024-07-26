package br.com.magalzim.neoforum.form

import br.com.magalzim.neoforum.model.TopicStatus

object UpdateTopicFormTest {
    fun build() = UpdateTopicForm(
        id = 1,
        title = "Este tópico vai explodir!",
        message = "This topic was closed by the administrator",
        status = TopicStatus.CLOSED
    )
    fun empty() = UpdateTopicForm(
        id = null,
        title = null,
        message = null,
        status = TopicStatus.CLOSED
    )
}