package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.model.Answer
import br.com.magalzim.neoforum.service.AnswerService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name="bearerAuth")
class AnswerController(private val service: AnswerService) {

    @PostMapping
    fun add(@RequestBody @Valid answer: Answer) = service.add(answer)

}