package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.NewAuthorForm
import br.com.magalzim.neoforum.form.UpdateAuthorForm
import br.com.magalzim.neoforum.mapper.AuthorFormMapper
import br.com.magalzim.neoforum.mapper.AuthorViewMapper
import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.AuthorRepository
import br.com.magalzim.neoforum.view.AuthorByRoleView
import br.com.magalzim.neoforum.view.AuthorView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AuthorService(
    private val repository: AuthorRepository,
    private val authorViewMapper: AuthorViewMapper,
    private val authorFormMapper: AuthorFormMapper,
    private val userRoleService: UserRoleService,
) {
    fun findById(id: Long?): Author {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw NotFoundException(Author::class)
    }

    fun find(id: Long): AuthorView {
        return authorViewMapper.map(findById(id))
    }

    fun list(pagination: Pageable): Page<AuthorView> {
        val users = repository.findAll(pagination)
        return users.map { user -> authorViewMapper.map(user) }
    }

    fun add(dto: NewAuthorForm): AuthorView {
        val author = authorFormMapper.map(dto)
        val result = repository.save(author)
        userRoleService.default(result.id!!)
        return authorViewMapper.map(author)
    }

    fun update(dto: UpdateAuthorForm): AuthorView {
        if (dto.id != null) {
            val model = repository.findById(dto.id)
                .orElseThrow{ NotFoundException(Author::class) }
            model.name = dto.name.toString()
            model.password = dto.password.toString()
            model.email = dto.email.toString()
            model.avatar = dto.avatar.toString()
            repository.save(model)
            return authorViewMapper.map(model)
        }
        throw NotFoundException(Author::class)
    }

    fun roles(id: Long): List<AuthorByRoleView> = repository.findAuthorWithRoles(id)

}