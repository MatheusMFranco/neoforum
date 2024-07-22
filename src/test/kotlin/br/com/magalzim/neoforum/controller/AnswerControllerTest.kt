package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.config.JWTUtil
import br.com.magalzim.neoforum.configuration.DatabaseContainerConfiguration
import br.com.magalzim.neoforum.model.AuthorTest
import br.com.magalzim.neoforum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AnswerControllerTest: DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {
        private const val RESOURCE = "/answers"
    }

    @BeforeEach
    fun setup() {
        token = generateToken()
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    @Test
    fun `should return code 400 when call answers without token`() {
        mockMvc.get(RESOURCE).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `should return code 200 when call answers with token`() {
        mockMvc.get(RESOURCE.plus("%s").format("/1")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { isOk() } }
    }

    private fun generateToken(): String {
        val authorities = mutableListOf(Role(1, "READ_AND_WRITE"))
        val user = AuthorTest.buildToToken()
        return jwtUtil.generateToken(user.email, authorities)
    }

}