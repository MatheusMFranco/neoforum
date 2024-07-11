package br.com.magalzim.neoforum.model

object AuthorTest {
    fun build() = Author(
        id = 1,
        name = "Fox",
        email = "fox@fox.com",
        avatar = "fox",
        password = "123456"
    )
    fun buildToToken() = Author(name = "Fox", email = "fox@fox.com", password = "123456", avatar= "fox")
}
