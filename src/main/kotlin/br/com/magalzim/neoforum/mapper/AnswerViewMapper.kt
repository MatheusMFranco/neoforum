package br.com.magalzim.neoforum.mapper

import br.com.magalzim.neoforum.model.Answer
import br.com.magalzim.neoforum.view.AnswerView
import org.springframework.stereotype.Component

@Component
class AnswerViewMapper: Mapper<Answer, AnswerView> {
    override fun map(t: Answer): AnswerView {
        return AnswerView(
            id = t.id,
            message = t.message,
            registerDate = t.registerDate,
            author = t.author.name
        )
    }
}