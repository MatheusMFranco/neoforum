package br.com.magalzim.neoforum.mapper

import br.com.magalzim.neoforum.model.Board
import br.com.magalzim.neoforum.view.BoardView
import org.springframework.stereotype.Component

@Component
class BoardViewMapper: Mapper<Board, BoardView> {
    override fun map(t: Board): BoardView {
        return BoardView(
            id = t.id,
            name = t.name,
            description = t.description,
            icon = t.icon.toString()
        )
    }
}