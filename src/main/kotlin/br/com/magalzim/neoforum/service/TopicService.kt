package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.NewTopicForm
import br.com.magalzim.neoforum.form.UpdateTopicForm
import br.com.magalzim.neoforum.mapper.TopicFormMapper
import br.com.magalzim.neoforum.mapper.TopicViewMapper
import br.com.magalzim.neoforum.repository.TopicRepository
import br.com.magalzim.neoforum.view.TopicByBoardView
import br.com.magalzim.neoforum.view.TopicView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic Not Found"
) {

    fun list(
        title: String?,
        pagination: Pageable
    ): Page<TopicView> {
        val topics = if (title == null) {
            repository.findAll(pagination)
        } else {
            repository.findByTitle(title, pagination)
        }
        return topics.map { topic -> topicViewMapper.map(topic) }
    }

    fun findById(id: Long): TopicView {
        val model = repository.findById(id)
            .orElseThrow{NotFoundException(notFoundMessage)}
        return topicViewMapper.map(model)
    }

    fun add(dto: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(dto)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    fun update(dto: UpdateTopicForm): TopicView {
        if (dto.id != null) {
            val model = repository.findById(dto.id)
                .orElseThrow{NotFoundException(notFoundMessage)}
            model.title = dto.title.toString()
            model.message = dto.message.toString()
            return topicViewMapper.map(model)
        }
        throw Exception()
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }


    fun forums(): List<TopicByBoardView> {
        return repository.forums()
    }


}