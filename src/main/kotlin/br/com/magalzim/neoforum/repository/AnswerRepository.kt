package br.com.magalzim.neoforum.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

import br.com.magalzim.neoforum.model.Answer

interface AnswerRepository: JpaRepository<Answer, Long> {
    fun findByTopicId(topicId: Long, pagination: Pageable): Page<Answer>
}