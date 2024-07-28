package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.UserRoleRepository
import org.springframework.stereotype.Service

@Service
class UserRoleService(private val repository: UserRoleRepository) {
    fun findById(id: Long?): UserRole {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw NotFoundException(UserRole::class)
    }

    fun findByAuthorId(id: Long): UserRole? {
        return try {
            repository.findByAuthorId(id)
        } catch (e: Exception){
            null
        }
    }

    fun default(author: Long) = saveUserRole(author, UserRoleAuthority.READ_AND_WRITE)
    fun default(userRole: UserRole) = updateUserRole(userRole, UserRoleAuthority.READ_AND_WRITE)
    fun premium(author: Long) = saveUserRole(author, UserRoleAuthority.PREMIUM)
    fun premium(userRole: UserRole) = updateUserRole(userRole, UserRoleAuthority.PREMIUM)
    fun monitor(author: Long) = saveUserRole(author, UserRoleAuthority.MONITOR)
    fun monitor(userRole: UserRole) = updateUserRole(userRole, UserRoleAuthority.MONITOR)

    private fun saveUserRole(author: Long, authority: UserRoleAuthority) {
        repository.save(UserRole(
            authorId = author,
            roleId = convertToId(authority)
        ))
    }

    private fun updateUserRole(userRole: UserRole, authority: UserRoleAuthority) {
        userRole.roleId = convertToId(authority)
        repository.save(userRole)
    }

    private fun convertToId(role: UserRoleAuthority) = role.ordinal.toLong() + 1

}