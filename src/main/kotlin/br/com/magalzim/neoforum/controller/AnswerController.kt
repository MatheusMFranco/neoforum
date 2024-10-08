package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.form.NewAnswerForm
import br.com.magalzim.neoforum.form.UpdateAnswerForm
import br.com.magalzim.neoforum.service.AnswerService
import br.com.magalzim.neoforum.view.AnswerView
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/answers")
@SecurityRequirement(name="bearerAuth")
class AnswerController(private val service: AnswerService) {

    @PostMapping
    @Transactional
    fun add(
        @RequestBody @Valid answer: NewAnswerForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AnswerView> {
        val view = service.add(answer)
        val uri = uriBuilder.path("/answers/${view.id}").build().toUri()
        return ResponseEntity.created(uri).body(view)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid answer: UpdateAnswerForm): ResponseEntity<AnswerView> {
        val view = service.update(answer)
        return ResponseEntity.ok(view)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @GetMapping("/{id}")
    fun list(
        @PathVariable id: Long,
        @PageableDefault(size = 25, sort = ["registerDate"], direction = Sort.Direction.DESC) pagination: Pageable,
    ): Page<AnswerView> {
        return service.list(id, pagination)
    }
}