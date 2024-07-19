package br.com.magalzim.neoforum.form

import br.com.magalzim.neoforum.validation.MessageValidation
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class NewAnswerForm(
    @field:NotEmpty(message = MessageValidation.MESSAGE_EMPTY)
    @field:NotNull(message = MessageValidation.MESSAGE_NULL)
    @field:Size(
        min = MessageValidation.MIN,
        max = MessageValidation.MAX,
        message = MessageValidation.MESSAGE
    )
    val message: String?,

    @field:NotNull(message = MessageValidation.TOPIC_NULL)
    val topicId: Long?,

    @field:Min(1, message = MessageValidation.AUTHOR_MIN)
    @field:NotNull(message = MessageValidation.AUTHOR_NULL)
    val authorId: Long?,
)
