package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.model.UserRole
import br.com.magalzim.neoforum.service.UserRoleService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.HttpStatus
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user-roles")
@SecurityRequirement(name="bearerAuth")
class UserRoleController(private val service: UserRoleService) {

    @PutMapping("/premium/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun premium(@PathVariable id: Long) {
        updateRole(id, service::premium, service::premium)
    }

    @PutMapping("/default/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun default(@PathVariable id: Long) {
        updateRole(id, service::default, service::default)
    }

    @PutMapping("/monitor/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun monitor(@PathVariable id: Long) {
        updateRole(id, service::monitor, service::monitor)
    }

    private fun updateRole(id: Long, update: (UserRole) -> Unit, save: (Long) -> Unit) {
        val role = service.findByAuthorId(id)
        if (role != null) update(role) else save(id)
    }
    
}