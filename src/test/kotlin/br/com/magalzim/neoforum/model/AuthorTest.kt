package br.com.magalzim.neoforum.model

import br.com.magalzim.neoforum.view.AuthorByRoleView

object AuthorTest {
    private fun create(name: String) = Author(
        id = 1,
        name = name,
        email = "fox@fox.com",
        avatar = "fox",
        password = "123456"
    )
    fun build() = create("Fox")
    fun updated() = create("Matt")
    fun buildToToken() = Author(name = "Fox", email = "admin@email.com", password = "123456", avatar= "fox")
    fun roles() = AuthorByRoleView(email = "admin@email.com", authorId = 2, roleName = UserRoleAuthority.ADMIN.name)
}
