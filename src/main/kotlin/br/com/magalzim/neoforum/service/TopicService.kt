package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.NewTopicForm
import br.com.magalzim.neoforum.form.UpdateTopicForm
import br.com.magalzim.neoforum.mapper.TopicFormMapper
import br.com.magalzim.neoforum.mapper.TopicViewMapper
import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.model.UserRole
import br.com.magalzim.neoforum.repository.TopicRepository
import br.com.magalzim.neoforum.view.TopicByBoardView
import br.com.magalzim.neoforum.view.TopicView
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val notFoundMessage: String = "Topic Not Found"
) {

    companion object {
        const val EVERYONE: Long = 0
        const val STAFF: Long = 1
        const val PREMIUM: Long = 11
    }

    @Cacheable(cacheNames = ["topics"], key = "#root.method.name")
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
        return topicViewMapper.map(find(id))
    }

    fun find(id: Long?): Topic {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw NotFoundException(notFoundMessage)
    }

    @CacheEvict(value=["topics"], allEntries = true)
    fun add(dto: NewTopicForm): TopicView {
        val topic = topicFormMapper.map(dto)
        repository.save(topic)
        return topicViewMapper.map(topic)
    }

    @CacheEvict(value=["topics"], allEntries = true)
    fun update(dto: UpdateTopicForm): TopicView {
        if (dto.id != null) {
            val model = repository.findById(dto.id)
                .orElseThrow{NotFoundException(notFoundMessage)}
            model.title = dto.title.toString()
            model.message = dto.message.toString()
            model.updateDate = LocalDateTime.now()
            return topicViewMapper.map(model)
        }
        throw Exception()
    }

    @CacheEvict(value=["topics"], allEntries = true)
    fun delete(id: Long) {
        repository.deleteById(id)
    }

    fun forums(): List<TopicByBoardView> {
        val roles = SecurityContextHolder
                .getContext()
                .authentication
                .authorities
                .map { it.authority }
        val list = when {
            roles.contains(UserRole.MONITOR.name) -> listOf(EVERYONE)
            roles.contains(UserRole.PREMIUM.name) -> listOf(STAFF)
            else -> listOf(STAFF, PREMIUM)
        }
        return repository.forums(list)
    }
}