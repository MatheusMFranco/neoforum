package br.com.magalzim.neoforum.repository

import br.com.magalzim.neoforum.model.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<Answer, Long>