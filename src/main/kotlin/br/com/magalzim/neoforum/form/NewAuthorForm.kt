package br.com.magalzim.neoforum.form

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewAuthorForm(
    @field:NotEmpty(message = "name must not be empty")
    @field:NotNull(message = "name is required")
    @field:Size(min = 5, max = 100, message = "name must be between 5 and 100 characters")
    val name: String?,

    @field:Email(message = "email must not be valid")
    @field:NotEmpty(message = "email must not be empty")
    @field:NotNull(message = "email is required")
    @field:Size(min = 5, max = 100, message = "email must be between 5 and 100 characters")
    val email: String?,

    @field:NotEmpty(message = "avatar must not be empty")
    @field:NotNull(message = "avatar is required")
    @field:Size(min = 5, max = 100, message = "avatar must be between 5 and 100 characters")
    val avatar: String?,

    @field:NotNull(message = "password is required")
    @field:Size(min = 8, max = 16, message = "password must be between 8 and 16 characters")
    val password: String?
)
