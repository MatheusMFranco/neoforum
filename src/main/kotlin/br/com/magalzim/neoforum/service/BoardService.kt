package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.UpdateBoardForm
import br.com.magalzim.neoforum.mapper.BoardViewMapper
import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.BoardRepository
import br.com.magalzim.neoforum.view.BoardView
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val repository: BoardRepository,
    private val boardViewMapper: BoardViewMapper
) {
    fun list(): List<Board> {
        return repository.findAll()
    }

    fun findById(id: Long?): Board {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw EntityNotFoundException()
    }

    fun update(dto: UpdateBoardForm): BoardView {
        if (dto.id != null) {
            val model = repository.findById(dto.id)
                .orElseThrow{NotFoundException(Answer::class)}
            model.name = dto.name.toString()
            model.description = dto.description.toString()
            model.icon = dto.icon.toString()
            repository.save(model)
            return boardViewMapper.map(model)
        }
        throw NotFoundException(Board::class)
    }

}