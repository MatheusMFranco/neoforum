package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.form.NewTopicForm
import br.com.magalzim.neoforum.form.UpdateTopicForm
import br.com.magalzim.neoforum.service.TopicService
import br.com.magalzim.neoforum.view.TopicByBoardView
import br.com.magalzim.neoforum.view.TopicView
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name="bearerAuth")
class TopicController(private val service: TopicService) {

    @GetMapping
    fun list(
        @RequestParam(required = false) title: String?,
        @PageableDefault(size = 5, sort = ["registerDate"], direction = Sort.Direction.DESC) pagination: Pageable,
    ): Page<TopicView> {
        return service.list(title, pagination)
    }

    @GetMapping("/board/{id}")
    fun list(
        @PathVariable id: Long,
        @PageableDefault(size = 5, sort = ["registerDate"], direction = Sort.Direction.DESC) pagination: Pageable,
    ): Page<TopicView> {
        return service.list(id, pagination)
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): TopicView {
        return service.findById(id)
    }

    @PostMapping
    @Transactional
    fun add(
        @RequestBody @Valid topic: NewTopicForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicView> {
        val view = service.add(topic)
        val uri = uriBuilder.path("/topics/${view.id}").build().toUri()
        return ResponseEntity.created(uri).body(view)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid topic: UpdateTopicForm): ResponseEntity<TopicView>  {
        val view = service.update(topic)
        return ResponseEntity.ok(view)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }

    @GetMapping("/forums")
    fun forums(): List<TopicByBoardView> {
        return service.forums()
    }

}