package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.form.NewAnswerForm
import br.com.magalzim.neoforum.mapper.AnswerFormMapper
import br.com.magalzim.neoforum.mapper.AnswerViewMapper
import br.com.magalzim.neoforum.repository.AnswerRepository
import br.com.magalzim.neoforum.view.AnswerView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val emailService: EmailService,
    private val repository: AnswerRepository,
    private val answerViewMapper: AnswerViewMapper,
    private val answerFormMapper: AnswerFormMapper
) {
    fun add(dto: NewAnswerForm): AnswerView {
        val answer = answerFormMapper.map(dto)
        repository.save(answer)
        val author = answer.topic.author
        emailService.notify(author.email)
        return answerViewMapper.map(answer)
    }

    fun list(
        topicId: Long,
        pagination: Pageable
    ): Page<AnswerView> {
        val messages = repository.findByTopicId(topicId, pagination)
        return messages.map { answer -> answerViewMapper.map(answer) }
    }
}
