package br.com.magalzim.neoforum.security

import br.com.magalzim.neoforum.config.JWTUtil
import br.com.magalzim.neoforum.model.Credentials
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JWTLoginFilter(
    private val authenticationManager: AuthenticationManager?,
    private val jwtUtil: JWTUtil,
) : UsernamePasswordAuthenticationFilter(authenticationManager) {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication? {
        val (username, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java)
        val token = UsernamePasswordAuthenticationToken(username, password)
        return authenticationManager?.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as UserDetails)
        val token = jwtUtil.generateToken(user.username, user.authorities)
        response?.addHeader("Authorization", "Bearer $token")
    }

}
