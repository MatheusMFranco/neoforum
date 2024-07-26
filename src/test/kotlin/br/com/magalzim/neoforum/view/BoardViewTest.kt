package br.com.magalzim.neoforum.view

import br.com.magalzim.neoforum.form.UpdateBoardFormTest

object BoardViewTest {
    fun build() = BoardView(
        id = 1,
        name = "Official",
        description = "Staff board",
        icon = "tnt"
    )

    fun updated() = BoardView(
        id = 1,
        name = UpdateBoardFormTest.build().name.toString(),
        description = UpdateBoardFormTest.build().description.toString(),
        icon = UpdateBoardFormTest.build().icon.toString()
    )
}