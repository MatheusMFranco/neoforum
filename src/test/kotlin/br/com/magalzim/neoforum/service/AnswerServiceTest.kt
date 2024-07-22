package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.form.NewAnswerFormTest
import br.com.magalzim.neoforum.mapper.AnswerFormMapper
import br.com.magalzim.neoforum.mapper.AnswerViewMapper
import br.com.magalzim.neoforum.model.AnswerTest
import br.com.magalzim.neoforum.repository.AnswerRepository
import br.com.magalzim.neoforum.view.AnswerViewTest
import io.mockk.*
import org.junit.jupiter.api.Test
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

class AnswerServiceTest {

    val answer = PageImpl(listOf(AnswerTest.build()))
    val newAnswer = AnswerTest.build()
    val pagination: Pageable = mockk()

    val emailService: EmailService = mockk{
        every { notify(any()) } just Runs
    }
    val answerRepository: AnswerRepository = mockk{
        every { save(any()) } returns newAnswer
        every { findByTopicId(any(), any()) } returns answer
        every { findAll(pagination) } returns answer
    }
    val answerViewMapper: AnswerViewMapper = mockk{
        every { map(any()) } returns AnswerViewTest.build()
    }
    val answerFormMapper: AnswerFormMapper = mockk{
        every { map(any()) } returns AnswerTest.build()
    }

    val answerService = AnswerService(
        emailService, answerRepository, answerViewMapper, answerFormMapper
    )

    @Test
    fun `should send email to topics author`() {
        answerService.add(NewAnswerFormTest.build())
        verify(exactly = 1) { answerFormMapper.map(any()) }
        verify(exactly = 1) { answerRepository.save(any()) }
        verify(exactly = 1) { emailService.notify(any()) }
        verify(exactly = 1) { answerViewMapper.map(any()) }
    }

    @Test
    fun `should list messages by topic`() {
        answerService.list(1, pagination)
        verify(exactly = 1) { answerRepository.findByTopicId(any(), any()) }
        verify(exactly = 1) { answerViewMapper.map(any()) }
        verify(exactly = 0) { answerRepository.findAll(pagination) }
    }

}