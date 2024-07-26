package br.com.magalzim.neoforum.form

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateBoardForm(

    @field:Min(1, message = "id must be greater than 0")
    @field:NotNull(message = "id is required")
    val id: Long?,

    @field:NotEmpty(message = "name must not be empty")
    @field:Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    val name: String?,

    @field:NotEmpty(message = "description must not be empty")
    @field:Size(min = 5, max = 255, message = "Message must be between 5 and 255 characters")
    val description: String?,

    @field:NotEmpty(message = "icon must not be empty")
    @field:Size(min = 5, max = 255, message = "icon must be between 5 and 255 characters")
    val icon: String?,
)
