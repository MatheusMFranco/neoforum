package br.com.magalzim.neoforum.mapper

import br.com.magalzim.neoforum.form.NewAnswerForm
import br.com.magalzim.neoforum.form.NewTopicForm
import br.com.magalzim.neoforum.model.Answer
import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.service.BoardService
import br.com.magalzim.neoforum.service.AuthorService
import br.com.magalzim.neoforum.service.TopicService
import org.springframework.stereotype.Component

@Component
class AnswerFormMapper(
    private val topicService: TopicService,
    private val authorService: AuthorService,
): Mapper<NewAnswerForm, Answer> {
    override fun map(t: NewAnswerForm): Answer {
        return Answer(
            message = t.message.toString(),
            topic = topicService.find(t.topicId),
            author = authorService.findById(t.authorId),
        )
    }
}