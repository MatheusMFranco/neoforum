package br.com.magalzim.neoforum.view

import java.io.Serializable

data class AuthorView(
    val id: Long?,
    val name: String,
    val email: String,
    val avatar: String
): Serializable