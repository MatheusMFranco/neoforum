package br.com.magalzim.neoforum.mapper

import br.com.magalzim.neoforum.form.NewTopicForm
import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.service.BoardService
import br.com.magalzim.neoforum.service.UserService
import org.springframework.stereotype.Component

@Component
class TopicFormMapper(
    private val boardService: BoardService,
    private val userService: UserService,
): Mapper<NewTopicForm, Topic> {
    override fun map(t: NewTopicForm): Topic {
        return Topic(
            title = t.title.toString(),
            message = t.message.toString(),
            board = boardService.findById(t.boardId),
            author = userService.findById(t.authorId)
        )
    }
}