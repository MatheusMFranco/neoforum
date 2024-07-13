package br.com.magalzim.neoforum.service

import br.com.magalzim.neoforum.model.*
import br.com.magalzim.neoforum.repository.AnswerRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

import java.util.*

@Service
class AnswerService(
    private val emailService: EmailService,
    private val repository: AnswerRepository
) {

    fun list(): List<Answer> {
        return repository.findAll()
    }

    fun findById(id: Long?): Answer {
        if (id != null) {
            return repository.getReferenceById(id)
        }
        throw EntityNotFoundException()
    }

    fun add(answer: Answer) {
        repository.save(answer)
        val author = answer.topic.author.email
        emailService.notify(author)
    }

}
