package br.com.magalzim.neoforum.form

object UpdateAnswerFormTest {
    fun build() = UpdateAnswerForm(
        id = 1,
        message = "Este tópico vai explodir!",
    )
    fun empty() = UpdateAnswerForm(
        id = null,
        message = null
    )
}