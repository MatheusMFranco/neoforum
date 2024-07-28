package br.com.magalzim.neoforum.form

import br.com.magalzim.neoforum.model.TopicStatus

object UpdateAuthorFormTest {
    fun build() = UpdateAuthorForm(
        id = 1,
        avatar = "fox",
        name = "Fox",
        email = "fox@fox.com",
        password = "fox@fox.com"
    )
    fun empty() = UpdateAuthorForm(
        id = null,
        avatar = null,
        name = null,
        email = null,
        password = null
    )
}