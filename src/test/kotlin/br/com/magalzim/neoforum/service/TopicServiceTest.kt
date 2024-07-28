package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.UpdateTopicFormTest
import br.com.magalzim.neoforum.mapper.TopicFormMapper
import br.com.magalzim.neoforum.mapper.TopicViewMapper
import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.model.TopicTest
import br.com.magalzim.neoforum.model.UserRoleAuthority
import br.com.magalzim.neoforum.repository.TopicRepository
import br.com.magalzim.neoforum.service.TopicService.Companion.EVERYONE
import br.com.magalzim.neoforum.service.TopicService.Companion.PREMIUM
import br.com.magalzim.neoforum.service.TopicService.Companion.STAFF
import br.com.magalzim.neoforum.view.TopicByBoardViewTest
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
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextHolder
import java.util.Optional

class TopicServiceTest {

    private val topics = PageImpl(listOf(TopicTest.build()))
    private val pagination: Pageable = mockk()

    private val forumsStaff = TopicByBoardViewTest.buildStaff()
    private val forumsPremium = TopicByBoardViewTest.buildPremium()
    private val forumsEveryone = TopicByBoardViewTest.buildEveryone()

    private val updatedTopic = TopicTest.updated()

    private val topicRepository: TopicRepository = mockk{
        every { findByTitle(any(), any()) } returns topics
        every { findByBoardId(any(), any()) } returns topics
        every { findAll(pagination) } returns topics
        every { forums(listOf(EVERYONE)) } returns forumsStaff
        every { forums(listOf(STAFF)) } returns forumsPremium
        every { forums(listOf(STAFF, PREMIUM)) } returns forumsEveryone
        every { findById(any()) } returns Optional.of(updatedTopic)
        every { save(any()) } returns updatedTopic
    }

    private val topicViewMapper: TopicViewMapper = mockk{
        every { map(any()) } returns TopicViewTest.build()
    }

    private val topicFormMapper: TopicFormMapper = mockk()

    private val topicService = TopicService(
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
            topicService.find(null)
        }
        Assertions.assertThat(current.message).isEqualTo("Topic ID not found!")
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

    @Test
    fun `should return forums for staff`() {
        val securityContext: SecurityContext = mockk(relaxed = true)
        val authentication = mockk<Authentication>()
        every { authentication.authorities } returns listOf(SimpleGrantedAuthority(UserRoleAuthority.MONITOR.name))
        every { securityContext.authentication } returns authentication
        SecurityContextHolder.setContext(securityContext)

        val result = topicService.forums()

        forumsStaff.forEachIndexed { index, it ->
            Assertions.assertThat(result[index].name).isEqualTo(it.name)
            Assertions.assertThat(result[index].amount).isEqualTo(it.amount)
        }
    }

    @Test
    fun `should return forums for premium`() {
        val securityContext: SecurityContext = mockk(relaxed = true)
        val authentication = mockk<Authentication>()
        every { authentication.authorities } returns listOf(SimpleGrantedAuthority(UserRoleAuthority.PREMIUM.name))
        every { securityContext.authentication } returns authentication
        SecurityContextHolder.setContext(securityContext)

        val result = topicService.forums()

        forumsPremium.forEachIndexed { index, it ->
            Assertions.assertThat(result[index].name).isEqualTo(it.name)
            Assertions.assertThat(result[index].amount).isEqualTo(it.amount)
        }
    }

    @Test
    fun `should return forums for everyone`() {
        val securityContext: SecurityContext = mockk(relaxed = true)
        val authentication = mockk<Authentication>()
        every { authentication.authorities } returns listOf(SimpleGrantedAuthority(UserRoleAuthority.READ_AND_WRITE.name))
        every { securityContext.authentication } returns authentication
        SecurityContextHolder.setContext(securityContext)

        val result = topicService.forums()

        forumsEveryone.forEachIndexed { index, it ->
            Assertions.assertThat(result[index].name).isEqualTo(it.name)
            Assertions.assertThat(result[index].amount).isEqualTo(it.amount)
        }
    }

    @Test
    fun `should update topic`() {
        every { topicViewMapper.map(any()) } returns TopicViewTest.updated()
        val updatedTopicView = topicService.update(UpdateTopicFormTest.build())
        Assertions.assertThat(updatedTopicView.title).isEqualTo("Este tópico vai explodir!")
    }

    @Test
    fun `should list topics by board`() {
        topicService.list(1, pagination)
        verify(exactly = 1) { topicRepository.findByBoardId(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 0) { topicRepository.findAll(pagination) }
    }

}