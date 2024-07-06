package br.com.magalzim.neoforum.model

import java.time.LocalDateTime

data class Topic(
    var id: Long? = null,
    val title: String,
    val message: String,
    val registerDate: LocalDateTime = LocalDateTime.now(),
    val board: Board,
    val author: User,
    val status: TopicStatus = TopicStatus.EMPTY,
    val answers: List<Answer> = ArrayList()
)