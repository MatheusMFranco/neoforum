package br.com.magalzim.neoforum.controller

import br.com.magalzim.neoforum.config.JWTUtil
import br.com.magalzim.neoforum.configuration.DatabaseContainerConfiguration
import br.com.magalzim.neoforum.model.AuthorTest
import br.com.magalzim.neoforum.model.Role
import br.com.magalzim.neoforum.model.UserRoleAuthority
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.*
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ControllerTest: DatabaseContainerConfiguration() {

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    private var token: String? = null

    companion object {
        private const val AUTHOR_RESOURCE = "/authors"
        private const val BOARD_RESOURCE = "/boards"
        private const val TOPIC_RESOURCE = "/topics"
        private const val ANSWER_RESOURCE = "/answers/1"
        private const val USEROLE_RESOURCE = "/user-roles"
        private const val ROLES_RESOURCE = "/roles/1"
    }

    @BeforeEach
    fun setup() {
        token = generateToken(UserRoleAuthority.READ_AND_WRITE)
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    @Test
    fun `should return code 201 when add author`() {
        val newAuthorJson = """
            {
                "name": "John Doe",
                "email": "john.does@example.com",
                "password": "password123",
                "avatar": "letitsnow"
            }
        """
        mockMvc.perform(MockMvcRequestBuilders.post(AUTHOR_RESOURCE)
            .contentType(MediaType.APPLICATION_JSON)
            .content(newAuthorJson))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun `should return code 200 when update author`() {
        val newAuthorJson = """
            {
                "id": 1,
                "name": "John Doe",
                "email": "john.doe@example.com",
                "password": "password123",
                "avatar": "letitsnow"
            }
        """
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.perform(MockMvcRequestBuilders.put(AUTHOR_RESOURCE)
            .header("Authorization", "Bearer $token")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newAuthorJson))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun `should return code 200 when update board`() {
        val newAuthorJson = """
            {
                "id": 4,
                "name": "Arte Special",
                "description": "Conteudo artisticamente artistico",
                "icon": "newTnt"
            }
        """
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.perform(MockMvcRequestBuilders.put(BOARD_RESOURCE)
            .header("Authorization", "Bearer $token")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newAuthorJson))
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful)
    }

    @Test
    fun `should return code 200 when login`() {
        val newTopicJson = """
            {
                "username": "admin@email.com",
                "password": "123456"
            }
        """
        mockMvc.perform(MockMvcRequestBuilders.post("/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(newTopicJson))
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    @Test
    fun `should return code 400 when call topics without token`() {
        mockMvc.get(TOPIC_RESOURCE).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `should return code 200 when call forums with token`() {
        mockMvc.get(TOPIC_RESOURCE.plus("%s").format("/forums")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { isOk() } }
    }

    @Test
    fun `should return code 200 when call topics by id and authenticated user`() {
        mockMvc.get(TOPIC_RESOURCE.plus("%s").format("/1")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { isOk() } }
    }

    @Test
    fun `should return code 200 when call topics by board and authenticated user`() {
        mockMvc.get(TOPIC_RESOURCE.plus("%s").format("/board/1")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { isOk() } }
    }

    @Test
    fun `should return code 400 when call answers without token`() {
        mockMvc.get(ANSWER_RESOURCE).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `should return code 200 when call answers with token`() {
        mockMvc.get(ANSWER_RESOURCE) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { isOk() } }
    }

    @Test
    fun `should return code 204 when set premium user`() {
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.put(USEROLE_RESOURCE.plus("%s").format("/premium/1")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `should return code 204 when set default user`() {
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.put(USEROLE_RESOURCE.plus("%s").format("/default/1")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `should return code 204 when set monitor user`() {
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.put(USEROLE_RESOURCE.plus("%s").format("/monitor/1")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `should return code 204 when find users by role`() {
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.get(ROLES_RESOURCE) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `should return code 204 when delete answer`() {
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.delete(ANSWER_RESOURCE) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `should return code 204 when delete board`() {
        token = generateToken(UserRoleAuthority.ADMIN)
        mockMvc.delete(BOARD_RESOURCE.plus("%s").format("/5")) {
            headers { this.setBearerAuth(token.toString()) }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(role: UserRoleAuthority): String {
        val authorities = mutableListOf(Role(role.ordinal.toLong() + 1, role.name))
        val user = AuthorTest.buildToToken()
        return jwtUtil.generateToken(user.email, authorities)
    }

}