package br.com.magalzim.neoforum.model

import java.time.LocalDateTime

data class Answer(
    val id: Long?,
    val message: String,
    val registerDate: LocalDateTime = LocalDateTime.now(),
    val author: User,
    val topic: Topic
)