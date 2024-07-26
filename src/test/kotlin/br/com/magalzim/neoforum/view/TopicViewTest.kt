package br.com.magalzim.neoforum.view

import br.com.magalzim.neoforum.model.TopicStatus
import br.com.magalzim.neoforum.util.DateUtil

object TopicViewTest {
    private fun create(title: String) = TopicView(
        id = 1,
        title = title,
        message = "Será que este tópico vai explodir?",
        status = TopicStatus.EMPTY,
        registerDate = DateUtil.mock,
        updateDate = DateUtil.mock
    )
    fun build() = create("Vamos estourar!")
    fun updated() = create("Este tópico vai explodir!")
}