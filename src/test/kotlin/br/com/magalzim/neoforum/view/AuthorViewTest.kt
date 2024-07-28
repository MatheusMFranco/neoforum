package br.com.magalzim.neoforum.view

object AuthorViewTest {
    private fun create(name: String) = AuthorView(
        id = 1,
        name = name,
        email = "fox@fox.com",
        avatar = "fox"
    )
    fun build() = create("Fox")
    fun updated() = create("Matt")
}