package br.com.magalzim.neoforum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Answer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var message: String,
    val registerDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val author: Author,
    @ManyToOne
    val topic: Topic
)