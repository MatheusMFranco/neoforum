package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.exception.NotFoundException
import br.com.magalzim.neoforum.form.UpdateBoardFormTest
import br.com.magalzim.neoforum.mapper.BoardViewMapper
import br.com.magalzim.neoforum.model.BoardTest
import br.com.magalzim.neoforum.repository.BoardRepository
import br.com.magalzim.neoforum.view.BoardViewTest
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

import java.util.Optional

class BoardServiceTest {

    private val updatedBoard = BoardTest.updated()

    private var boardRepository: BoardRepository = mockk{
        every { findById(any()) } returns Optional.of(updatedBoard)
        every { save(any()) } returns updatedBoard
    }

    private var boardViewMapper: BoardViewMapper = mockk{
        every { map(any()) } returns BoardViewTest.updated()
    }

    private val boardService = BoardService(boardRepository, boardViewMapper)

    @Test
    fun `should update board`() {
        val updatedBoardView = boardService.update(UpdateBoardFormTest.build())
        Assertions.assertEquals("Spectrum Plot", updatedBoardView.name)
    }

    @Test
    fun `should throw not found exception when board does not found`() {
        every { boardRepository.findById(any()) } returns Optional.empty()
        val current = assertThrows<NotFoundException> {
            boardService.update(UpdateBoardFormTest.empty())
        }
        Assertions.assertEquals(current.message, "Board ID not found!")
    }

}