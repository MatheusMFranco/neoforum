package br.com.magalzim.neoforum.model

import java.time.LocalDate

data class User(
    val id: Long? = null,
    val name: String,
    val email: String,
    val avatar: String,
    val registerDate: LocalDate = LocalDate.now()
)
