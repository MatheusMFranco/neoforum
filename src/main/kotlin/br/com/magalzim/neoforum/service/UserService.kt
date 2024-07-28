package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.AuthorRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: AuthorRepository,
): UserDetailsService {
    override fun loadUserByUsername(username: String?): UserDetails {
        val author = repository.findByEmail(username)?: throw NotFoundException(Author::class)
        return UserDetail(author)
    }

    fun findById(id: Long?): Author {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw Exception()
    }
}