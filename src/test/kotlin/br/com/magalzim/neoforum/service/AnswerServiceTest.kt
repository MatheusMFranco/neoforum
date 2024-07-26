package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.NewAnswerFormTest
import br.com.magalzim.neoforum.form.UpdateAnswerFormTest
import br.com.magalzim.neoforum.mapper.AnswerFormMapper
import br.com.magalzim.neoforum.mapper.AnswerViewMapper
import br.com.magalzim.neoforum.model.AnswerTest
import br.com.magalzim.neoforum.repository.AnswerRepository
import br.com.magalzim.neoforum.view.AnswerViewTest
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable

import java.util.Optional

class AnswerServiceTest {

    private val answer = PageImpl(listOf(AnswerTest.build()))
    private val newAnswer = AnswerTest.build()
    private val updatedAnswer = AnswerTest.updated()
    private val pagination: Pageable = mockk()

    private val emailService: EmailService = mockk{
        every { notify(any()) } just Runs
    }

    private var answerRepository: AnswerRepository = mockk{
        every { findById(any()) } returns Optional.of(updatedAnswer)
        every { save(any()) } returns newAnswer
        every { findByTopicId(any(), any()) } returns answer
        every { findAll(pagination) } returns answer
    }

    private var answerViewMapper: AnswerViewMapper = mockk{
        every { map(any()) } returns AnswerViewTest.build()
    }

    private val answerFormMapper: AnswerFormMapper = mockk{
        every { map(any()) } returns AnswerTest.build()
    }

    private val answerService = AnswerService(
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

    @Test
    fun `should update message`() {
        every { answerRepository.save(any()) } returns updatedAnswer
        every { answerViewMapper.map(any()) } returns AnswerViewTest.updated()

        val updatedAnswerView = answerService.update(UpdateAnswerFormTest.build())

        Assertions.assertEquals("Este tópico vai explodir!", updatedAnswerView.message)
    }

    @Test
    fun `should throw not found exception when answer does not found`() {
        every { answerRepository.findById(any()) } returns Optional.empty()
        val current = assertThrows<NotFoundException> {
            answerService.update(UpdateAnswerFormTest.empty())
        }
        Assertions.assertEquals(current.message, "Answer ID not found!")
    }

}