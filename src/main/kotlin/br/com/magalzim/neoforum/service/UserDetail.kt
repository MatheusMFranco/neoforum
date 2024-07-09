package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.model.Author
import org.springframework.security.core.userdetails.UserDetails

class UserDetail(
    private val author: Author
): UserDetails {
    override fun getAuthorities() = author.role

    override fun getPassword() = author.password

    override fun getUsername() = author.email
}