package br.com.magalzim.neoforum.repository

import br.com.magalzim.neoforum.model.Author
import br.com.magalzim.neoforum.view.AuthorByRoleView
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuthorRepository: JpaRepository<Author, Long> {
    fun findByEmail(username: String?): Author?

    @Query("SELECT new br.com.magalzim.neoforum.view.AuthorByRoleView(a.id, a.email, r.name) " +
            "FROM Author a JOIN a.role r WHERE a.id = :authorId")
    fun findAuthorWithRoles(@Param("authorId") authorId: Long): List<AuthorByRoleView>
}