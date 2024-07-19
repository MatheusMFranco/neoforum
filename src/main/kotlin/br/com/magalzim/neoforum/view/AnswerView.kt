package br.com.magalzim.neoforum.view

import java.io.Serializable
import java.time.LocalDateTime

data class AnswerView(
    val id: Long?,
    val message: String,
    val registerDate: LocalDateTime,
    val author: String,
): Serializable