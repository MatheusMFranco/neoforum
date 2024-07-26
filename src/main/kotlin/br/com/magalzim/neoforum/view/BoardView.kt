package br.com.magalzim.neoforum.view

import java.io.Serializable

data class BoardView(
    val id: Long?,
    val name: String,
    val description: String,
    val icon: String,
): Serializable