package br.com.magalzim.neoforum.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import java.time.LocalDate

@Entity
data class Author(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val email: String,
    val avatar: String,
    val registerDate: LocalDate = LocalDate.now(),
    val password: String,
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_role",
        joinColumns = [JoinColumn(name = "author_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    val role: List<Role> = mutableListOf()
)
