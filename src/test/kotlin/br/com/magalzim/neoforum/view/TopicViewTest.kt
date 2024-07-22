package br.com.magalzim.neoforum.view

import br.com.magalzim.neoforum.model.TopicStatus
import br.com.magalzim.neoforum.util.DateUtil
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = TopicView(
        id = 1,
        title = "Vamos estourar!",
        message = "Será que este tópico vai explodir?",
        status = TopicStatus.EMPTY,
        registerDate = DateUtil.mock,
        updateDate = DateUtil.mock
    )
}