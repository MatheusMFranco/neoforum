package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.NewTopicForm
import br.com.magalzim.neoforum.form.UpdateTopicForm
import br.com.magalzim.neoforum.mapper.TopicFormMapper
import br.com.magalzim.neoforum.mapper.TopicViewMapper
import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.view.TopicView
import org.springframework.stereotype.Service

import java.util.*
import java.util.stream.Collectors

@Service
class TopicService(
    private var topics: List<Topic> = ArrayList(),
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic Not Found"
) {

    fun list(): List<TopicView> {
        return topics.stream().map { topic -> topicViewMapper.map(topic) }
            .collect(Collectors.toList())
    }

    fun findById(id: Long): TopicView {
        val model = topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        return topicViewMapper.map(model)
    }

    fun add(dto: NewTopicForm): TopicView {
        val id = topics.size.toLong() + 1
        val topic = topicFormMapper.map(dto)
        topic.id = id
        topics = topics.plus(topic)
        return topicViewMapper.map(topic)
    }

    fun update(dto: UpdateTopicForm): TopicView {
        val model = topics.stream().filter { topic ->
            topic.id == dto.id
        }.findFirst().get()
        val newTopic = Topic(
            id = dto.id,
            title = dto.title.toString(),
            message = dto.message.toString(),
            board = model.board,
            author = model.author
        )
        topics = topics.minus(model).plus(newTopic)
        return topicViewMapper.map(newTopic)
    }

    fun delete(id: Long) {
        val model = topics.stream().filter { topic ->
            topic.id == id
        }.findFirst().orElseThrow{NotFoundException(notFoundMessage)}
        topics = topics.minus(model)
    }

}