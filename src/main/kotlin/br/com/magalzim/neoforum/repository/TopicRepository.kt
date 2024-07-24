package br.com.magalzim.neoforum.repository

import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.view.TopicByBoardView
import io.lettuce.core.dynamic.annotation.Param
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByTitle(name: String, pagination: Pageable): Page<Topic>

    @Query("SELECT new br.com.magalzim.neoforum.view.TopicByBoardView(board.name, count(t)) FROM Topic t JOIN t.board board WHERE board.id NOT IN :ids GROUP BY board.name")
    fun forums(@Param("ids") ids: List<Long> = emptyList()): List<TopicByBoardView>
}