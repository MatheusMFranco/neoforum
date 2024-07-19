package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.BoardRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

import java.util.*

@Service
class BoardService(private val repository: BoardRepository) {
    fun list(): List<Board> {
        return repository.findAll()
    }

    fun findById(id: Long?): Board {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw EntityNotFoundException()
    }
}