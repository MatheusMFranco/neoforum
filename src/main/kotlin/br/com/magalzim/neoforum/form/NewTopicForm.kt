package br.com.magalzim.neoforum.form

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewTopicForm(
    @field:NotEmpty(message = "Title must not be empty")
    @field:NotNull(message = "Title is required")
    @field:Size(min = 5, max = 100, message = "Title must be between 5 and 100 characters")
    val title: String?,

    @field:NotEmpty(message = "Message must not be empty")
    @field:NotNull(message = "message is required")
    @field:Size(min = 5, max = 255, message = "Message must be between 5 and 255 characters")
    val message: String?,

    @field:Min(1, message = "BoardId must be greater than 0")
    @field:NotNull(message = "BoardId is required")
    val boardId: Long?,

    @field:Min(1, message = "AuthorId must be greater than 0")
    @field:NotNull(message = "authorId is required")
    val authorId: Long?,
)
