package br.com.magalzim.neoforum.mapper

import br.com.magalzim.neoforum.model.Author
import br.com.magalzim.neoforum.view.AuthorView
import org.springframework.stereotype.Component

@Component
class AuthorViewMapper: Mapper<Author, AuthorView> {
    override fun map(t: Author): AuthorView {
        return AuthorView(
            id = t.id,
            name = t.name,
            email = t.email,
            avatar = t.avatar
        )
    }
}