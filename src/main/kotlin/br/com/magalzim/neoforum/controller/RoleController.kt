package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.service.AuthorService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/roles")
class RoleController(private val service: AuthorService) {

    @GetMapping("/{id}")
    fun roles(@PathVariable id: Long, model: Model): String {
        model.addAttribute("authorsByRoles", service.roles(id))
        return "authors"
    }

}