package br.com.magalzim.neoforum.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Topic(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var title: String,
    var message: String,
    val registerDate: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val board: Board,
    @ManyToOne
    val author: Author,
    @Enumerated(value = EnumType.STRING)
    val status: TopicStatus = TopicStatus.EMPTY,
    @OneToMany(mappedBy = "topic")
    val answers: List<Answer> = ArrayList(),
    var updateDate: LocalDateTime? = null
)