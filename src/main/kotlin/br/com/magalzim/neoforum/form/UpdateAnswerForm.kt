package br.com.magalzim.neoforum.form

import br.com.magalzim.neoforum.validation.MessageValidation
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateAnswerForm(

    @field:Min(1, message = "id must be greater than 0")
    @field:NotNull(message = "id is required")
    val id: Long?,

    @field:NotEmpty(message = MessageValidation.MESSAGE_EMPTY)
    @field:NotNull(message = MessageValidation.MESSAGE_NULL)
    @field:Size(min = MessageValidation.MIN, max = MessageValidation.MAX, message = MessageValidation.MESSAGE)
    val message: String?,
)
