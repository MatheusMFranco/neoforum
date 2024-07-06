package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.model.*
import org.springframework.stereotype.Service

import java.util.*

@Service
class UserService(private var users: List<User> = ArrayList()) {

    init {
        users = listOf(User(
            id = 1,
            name = "Fox",
            email = "fox@fox.com",
            avatar = "fox",
        ))
    }

    fun list(): List<User> {
        return users
    }

    fun findById(id: Long?): User {
        return users.stream().filter { user ->
            user.id == id
        }.findFirst().get()
    }

}