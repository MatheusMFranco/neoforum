package br.com.magalzim.neoforum.form

import br.com.magalzim.neoforum.validation.MessageValidation
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewTopicForm(
    @field:NotEmpty(message = "title must not be empty")
    @field:NotNull(message = "title is required")
    @field:Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    val title: String?,

    @field:NotEmpty(message = MessageValidation.MESSAGE_EMPTY)
    @field:NotNull(message = MessageValidation.MESSAGE_NULL)
    @field:Size(min = MessageValidation.MIN, max = MessageValidation.MAX, message = MessageValidation.MESSAGE)
    val message: String?,

    @field:Min(1, message = "boardId must be greater than 0")
    @field:NotNull(message = "boardId is required")
    val boardId: Long?,

    @field:Min(1, message = "authorId must be greater than 0")
    @field:NotNull(message = "authorId is required")
    val authorId: Long?,
)
