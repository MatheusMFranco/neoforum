package br.com.magalzim.neoforum.view

import br.com.magalzim.neoforum.model.TopicStatus
import java.time.LocalDateTime

object TopicViewTest {
    fun build() = TopicView(
        id = 1,
        title = "Vamos estourar!",
        message = "Será que este tópico vai explodir?",
        status = TopicStatus.EMPTY,
        registerDate = LocalDateTime.now(),
        updateDate = LocalDateTime.now()
    )
}