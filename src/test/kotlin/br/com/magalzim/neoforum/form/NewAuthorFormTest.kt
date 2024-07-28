package br.com.magalzim.neoforum.form


object NewAuthorFormTest {
    fun build() = NewAuthorForm(
        name = "Fox",
        email = "fox@fox.com",
        avatar = "fox",
        password = "123456",
    )
}