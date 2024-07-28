package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.form.NewAuthorForm
import br.com.magalzim.neoforum.form.UpdateAuthorForm
import br.com.magalzim.neoforum.service.AuthorService
import br.com.magalzim.neoforum.view.AuthorView
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/authors")
@SecurityRequirement(name="bearerAuth")
class AuthorController(private val service: AuthorService) {

    @GetMapping
    fun list(
        @PageableDefault(size = 5, sort = ["registerDate"], direction = Sort.Direction.DESC) pagination: Pageable,
    ): Page<AuthorView> {
        return service.list(pagination)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): AuthorView {
        return service.find(id)
    }

    @PostMapping
    @Transactional
    fun add(
        @RequestBody @Valid author: NewAuthorForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<AuthorView> {
        val view = service.add(author)
        val uri = uriBuilder.path("/authors/${view.id}").build().toUri()
        return ResponseEntity.created(uri).body(view)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid author: UpdateAuthorForm): ResponseEntity<AuthorView>  {
        val view = service.update(author)
        return ResponseEntity.ok(view)
    }

}