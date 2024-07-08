package br.com.magalzim.neoforum.repository

import br.com.magalzim.neoforum.model.Topic
import br.com.magalzim.neoforum.view.TopicByBoardView
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByTitle(name: String, pagination: Pageable): Page<Topic>

    @Query("SELECT new br.com.magalzim.neoforum.view.TopicByBoardView(board.name, count(t)) FROM Topic t JOIN t.board board GROUP BY board.name")
    fun forums(): List<TopicByBoardView>
}