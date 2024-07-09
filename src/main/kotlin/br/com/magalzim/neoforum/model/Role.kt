package br.com.magalzim.neoforum.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority

@Entity
data class Role(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,
    private val name: String
): GrantedAuthority {
    override fun getAuthority() = name
}