package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.AuthorRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorService(private val repository: AuthorRepository): UserDetailsService {

    fun findById(id: Long?): Author {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw EntityNotFoundException()
    }

    override fun loadUserByUsername(username: String?): UserDetails {
        val author = repository.findByEmail(username)?: throw RuntimeException()
        return UserDetail(author)
    }

}