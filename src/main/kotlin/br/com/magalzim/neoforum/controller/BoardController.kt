package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.form.UpdateBoardForm
import br.com.magalzim.neoforum.service.BoardService
import br.com.magalzim.neoforum.view.BoardView
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/boards")
class BoardController(private val service: BoardService) {

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid board: UpdateBoardForm): ResponseEntity<BoardView> {
        val view = service.update(board)
        return ResponseEntity.ok(view)
    }

}