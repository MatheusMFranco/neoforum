package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.mapper.TopicFormMapper
import br.com.magalzim.neoforum.mapper.TopicViewMapper
import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.model.TopicTest
import br.com.magalzim.neoforum.repository.TopicRepository
import br.com.magalzim.neoforum.view.TopicViewTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.Optional

class TopicServiceTest {

    val topics = PageImpl(listOf(TopicTest.build()))
    val pagination: Pageable = mockk()

    val topicRepository: TopicRepository = mockk{
        every { findByTitle(any(), any()) } returns topics
        every { findAll(pagination) } returns topics
    }
    val topicViewMapper: TopicViewMapper = mockk{
        every { map(any()) } returns TopicViewTest.build()
    }
    val topicFormMapper: TopicFormMapper = mockk()

    val topicService = TopicService(
        topicRepository, topicViewMapper, topicFormMapper
    )

    @Test
    fun `should list topics by title`() {
        topicService.list("Vamos estourar!", pagination)
        verify(exactly = 1) { topicRepository.findByTitle(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findAll(pagination) }
    }

    @Test
    fun `should list all topics when the title is null`(){
        topicService.list(null, pagination)
        verify(exactly = 0) { topicRepository.findByTitle(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 1) { topicRepository.findAll(pagination) }
    }

    @Test
    fun `should throw not found exception when topic is not found`() {
        every { topicRepository.findById(any()) } returns Optional.empty()
        val current = assertThrows<NotFoundException> {
            topicService.findById(1)
        }
        Assertions.assertThat(current.message).isEqualTo("Topic Not Found")
    }

    @Test
    fun `should topic title be Vamos Estourar`() {
        val slot = slot<Topic>()
        every { topicViewMapper.map(capture(slot)) } returns TopicViewTest.build()
        topicService.list("Vamos estourar!", pagination)
        val topic = TopicTest.build()
        Assertions.assertThat(slot.captured.title).isEqualTo(topic.title)
        Assertions.assertThat(slot.captured.message).isEqualTo(topic.message)
        Assertions.assertThat(slot.captured.status).isEqualTo(topic.status)
    }

}