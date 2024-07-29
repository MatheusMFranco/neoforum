package br.com.magalzim.neoforum.integration

import br.com.magalzim.neoforum.model.AnswerTest
import br.com.magalzim.neoforum.model.AuthorTest
import br.com.magalzim.neoforum.model.TopicTest
import br.com.magalzim.neoforum.repository.AnswerRepository
import br.com.magalzim.neoforum.repository.AuthorRepository
import br.com.magalzim.neoforum.repository.TopicRepository
import br.com.magalzim.neoforum.view.TopicByBoardView
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@Testcontainers
@Transactional
class RepositoryTest {

    @Autowired
    private lateinit var answerRepository: AnswerRepository

    @Autowired
    private lateinit var topicRepository: TopicRepository

    @Autowired
    private lateinit var authorRepository: AuthorRepository

    private val answer = AnswerTest.build()
    private val topic = TopicTest.build()
    private val author = AuthorTest.build()

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testdb")
            withUsername("fox")
            withPassword("123456")
            start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
        }

        @AfterAll
        @JvmStatic
        fun tearDown() {
            mysqlContainer.stop()
        }
    }

    @Test
    fun `should list answers by topic`() {
        answerRepository.save(answer)
        val answer = answerRepository.findByTopicId(1, PageRequest.of(0, 5))
        Assertions.assertThat(answer).isNotEmpty
    }

    @Test
    fun `should generate forums`() {
        topicRepository.save(topic)
        val forums = topicRepository.forums()
        Assertions.assertThat(forums).isNotNull
        Assertions.assertThat(forums.first()).isExactlyInstanceOf(TopicByBoardView::class.java)
    }

    @Test
    fun `should list topic by title`() {
        topicRepository.save(topic)
        val foundTopics = topicRepository.findByTitle(topic.title, PageRequest.of(0, 5))
        Assertions.assertThat(foundTopics).isNotEmpty
    }

    @Test
    fun `should list topics by board`() {
        topicRepository.save(topic)
        val foundTopics = topicRepository.findByBoardId(1, PageRequest.of(0, 5))
        Assertions.assertThat(foundTopics).isNotEmpty
    }

    @Test
    fun `should list authors by role`() {
        val foundAuthors = authorRepository.findAuthorWithRoles(1)
        Assertions.assertThat(foundAuthors).isNotEmpty
    }
}