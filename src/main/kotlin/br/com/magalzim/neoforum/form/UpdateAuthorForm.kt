package br.com.magalzim.neoforum.form

import jakarta.validation.constraints.*

data class UpdateAuthorForm(
    @field:Min(1, message = "id must be greater than 0")
    @field:NotNull(message = "id is required")
    val id: Long?,

    @field:NotEmpty(message = "name must not be empty")
    @field:Size(min = 5, max = 100, message = "name must be between 5 and 100 characters")
    val name: String?,

    @field:Email(message = "email must not be valid")
    @field:NotEmpty(message = "email must not be empty")
    @field:Size(min = 5, max = 100, message = "email must be between 5 and 100 characters")
    val email: String?,

    @field:NotEmpty(message = "avatar must not be empty")
    @field:Size(min = 5, max = 100, message = "avatar must be between 5 and 100 characters")
    val avatar: String?,

    @field:Size(min = 8, max = 16, message = "password must be between 8 and 16 characters")
    val password: String?
)
