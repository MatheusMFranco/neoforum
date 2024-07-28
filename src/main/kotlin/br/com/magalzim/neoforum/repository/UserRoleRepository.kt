package br.com.magalzim.neoforum.repository

import br.com.magalzim.neoforum.model.UserRole
import org.springframework.data.jpa.repository.JpaRepository

interface UserRoleRepository: JpaRepository<UserRole, Long> {
    fun findByAuthorId(authorId: Long?): UserRole?
}