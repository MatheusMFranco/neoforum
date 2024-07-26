package br.com.magalzim.neoforum.form

object UpdateBoardFormTest {
    fun build() = UpdateBoardForm(
        id = 1,
        name = "Spectrum Plot",
        description = "This board was closed by the administrator",
        icon = "spectrum"
    )
    fun empty() = UpdateBoardForm(
        id = null,
        name = null,
        description = null,
        icon = null
    )
}