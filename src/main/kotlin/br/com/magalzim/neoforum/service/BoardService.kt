package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.model.*
import org.springframework.stereotype.Service

import java.util.*

@Service
class BoardService(private var boards: List<Board> = ArrayList()) {

    init {
        boards = listOf(Board(
            id = 1,
            name = "Writters",
            description = "Writters Board"
        ))
    }

    fun list(): List<Board> {
        return boards
    }

    fun findById(id: Long?): Board {
        return boards.stream().filter { board ->
            board.id == id
        }.findFirst().get()
    }

}