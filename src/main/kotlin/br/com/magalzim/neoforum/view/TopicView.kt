package br.com.magalzim.neoforum.view

import br.com.magalzim.neoforum.model.TopicStatus
import java.time.LocalDateTime

data class TopicView(
    val id: Long?,
    val title: String,
    val message: String,
    val registerDate: LocalDateTime,
    val status: TopicStatus,
)