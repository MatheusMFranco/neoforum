package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.NewAuthorFormTest
import br.com.magalzim.neoforum.form.UpdateAuthorFormTest
import br.com.magalzim.neoforum.mapper.AuthorFormMapper
import br.com.magalzim.neoforum.mapper.AuthorViewMapper
import br.com.magalzim.neoforum.model.Author
import br.com.magalzim.neoforum.model.AuthorTest
import br.com.magalzim.neoforum.model.UserRole
import br.com.magalzim.neoforum.repository.AuthorRepository
import br.com.magalzim.neoforum.view.AuthorViewTest
import io.mockk.*
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.Optional

class AuthorServiceTest {

    private val authors = PageImpl(listOf(AuthorTest.build()))
    private val pagination: Pageable = mockk()

    private val updatedAuthor = AuthorTest.updated()
    private val newAuthor = AuthorTest.build()

    private val authorRepository: AuthorRepository = mockk{
        every { findAll(pagination) } returns authors
        every { findByEmail(any()) } returns newAuthor
        every { getReferenceById(any()) } returns newAuthor
        every { findById(any()) } returns Optional.of(updatedAuthor)
        every { save(any()) } returns updatedAuthor
    }

    private val authorViewMapper: AuthorViewMapper = mockk{
        every { map(any()) } returns AuthorViewTest.build()
    }

    private val authorFormMapper: AuthorFormMapper = mockk{
        every { map(any()) } returns AuthorTest.build()
    }

    private val userRoleService: UserRoleService = mockk{
        every { default(1) } just Runs
        every { default(UserRole(1, 1, 1)) } just Runs
        every { monitor(1) } just Runs
        every { monitor(UserRole(1, 1, 2)) } just Runs
        every { premium(1) } just Runs
        every { premium(UserRole(1, 1, 3)) } just Runs
    }

    private val authorService = AuthorService(
        authorRepository,
        authorViewMapper,
        authorFormMapper,
        userRoleService
    )

    @Test
    fun `should list all authors`(){
        authorService.list(pagination)
        verify(exactly = 1) { authorViewMapper.map(any()) }
        verify(exactly = 1) { authorRepository.findAll(pagination) }
    }

    @Test
    fun `should find an author`(){
        authorService.findById(1)
        verify(exactly = 1) { authorRepository.getReferenceById(1) }
    }

    @Test
    fun `should not find an author`(){
        val current = assertThrows<NotFoundException> {
            authorService.findById(null)
        }
        Assertions.assertThat(current.message).isEqualTo("Author ID not found!")
    }

    @Test
    fun `should author name be Fox`() {
        val slot = slot<Author>()
        every { authorViewMapper.map(capture(slot)) } returns AuthorViewTest.build()
        authorService.find(1)
        val author = AuthorTest.build()
        Assertions.assertThat(slot.captured.name).isEqualTo(author.name)
        Assertions.assertThat(slot.captured.email).isEqualTo(author.email)
        Assertions.assertThat(slot.captured.avatar).isEqualTo(author.avatar)
    }

    @Test
    fun `should add author`() {
        every { authorViewMapper.map(any()) } returns AuthorViewTest.build()
        val newAuthorView = authorService.add(NewAuthorFormTest.build())
        Assertions.assertThat(newAuthorView.name).isEqualTo("Fox")
    }

    @Test
    fun `should update author`() {
        every { authorViewMapper.map(any()) } returns AuthorViewTest.updated()
        val updatedAuthorView = authorService.update(UpdateAuthorFormTest.build())
        Assertions.assertThat(updatedAuthorView.name).isEqualTo("Matt")
    }

    @Test
    fun `should throw not found exception when author is not found`() {
        every { authorRepository.findById(any()) } returns Optional.empty()
        val current = assertThrows<NotFoundException> {
            authorService.update(UpdateAuthorFormTest.empty())
        }
        Assertions.assertThat(current.message).isEqualTo("Author ID not found!")
    }

}