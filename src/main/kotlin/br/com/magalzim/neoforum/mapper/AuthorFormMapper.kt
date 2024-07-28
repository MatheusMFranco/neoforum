package br.com.magalzim.neoforum.mapper

import br.com.magalzim.neoforum.form.NewAuthorForm
import br.com.magalzim.neoforum.form.NewTopicForm
import br.com.magalzim.neoforum.model.Author
import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.service.BoardService
import br.com.magalzim.neoforum.service.AuthorService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class AuthorFormMapper(private val passwordEncoder: PasswordEncoder): Mapper<NewAuthorForm, Author> {
    override fun map(t: NewAuthorForm): Author {
        return Author(
            name = t.name.toString(),
            email = t.email.toString(),
            avatar = t.avatar.toString(),
            password = passwordEncoder.encode(t.password.toString())
        )
    }
}