package br.com.magalzim.neoforum.form

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateTopicForm(

    @field:Min(1, message = "id must be greater than 0")
    @field:NotNull(message = "id is required")
    val id: Long?,

    @field:NotEmpty(message = "Title must not be empty")
    @field:NotNull(message = "title is required")
    @field:Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    val title: String?,

    @field:NotEmpty(message = "Message must not be empty")
    @field:NotNull(message = "message is required")
    @field:Size(min = 5, max = 255, message = "Message must be between 5 and 255 characters")
    val message: String?,
)
