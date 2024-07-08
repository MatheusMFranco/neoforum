package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.AuthorRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class AuthorService(private val repository: AuthorRepository) {

    fun findById(id: Long?): Author {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw EntityNotFoundException()
    }

}