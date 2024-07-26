package br.com.magalzim.neoforum.model

import br.com.magalzim.neoforum.form.UpdateBoardFormTest

object BoardTest {
    fun build() = Board(
        id = 1,
        name = "Official",
        description = "staff members will post about technical glitches, things going unexpectedly wrong etc"
    )
    fun updated() = Board(
        id = 1,
        name = UpdateBoardFormTest.build().name.toString(),
        description = UpdateBoardFormTest.build().name.toString()
    )
}
