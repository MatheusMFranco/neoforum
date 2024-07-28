package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.model.UserRole
import br.com.magalzim.neoforum.repository.UserRoleRepository
import io.mockk.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserRoleServiceTest {

    val userRole = UserRole(1, 1, 1);

    private val userRoleRepository: UserRoleRepository = mockk{
        every { save(any()) } returns userRole
        every { getReferenceById(any()) } returns userRole
    }

    private val userRoleService = UserRoleService(userRoleRepository)

    @Test
    fun `should find an role`(){
        userRoleService.findById(1)
        verify(exactly = 1) { userRoleRepository.getReferenceById(1) }
    }

    @Test
    fun `should add role`() {
        userRoleService.default(1)
        verify(exactly = 1) { userRoleRepository.save(userRole.copy(id = null)) }
    }

    @Test
    fun `should update role`() {
        userRoleService.default(userRole)
        verify(exactly = 1) { userRoleRepository.save(userRole) }
    }

}