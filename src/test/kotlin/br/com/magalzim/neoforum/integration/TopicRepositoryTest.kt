package br.com.magalzim.neoforum.integration

import br.com.magalzim.neoforum.model.TopicTest
import br.com.magalzim.neoforum.repository.TopicRepository
import br.com.magalzim.neoforum.view.TopicByBoardView
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {
    @Autowired
    private lateinit var topicRepository: TopicRepository

    private val topic = TopicTest.build()

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:latest").apply {
            withDatabaseName("testdb")
            withUsername("fox")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
        }
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

}