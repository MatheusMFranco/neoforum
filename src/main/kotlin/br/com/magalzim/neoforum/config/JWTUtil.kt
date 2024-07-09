package br.com.magalzim.neoforum.config

import br.com.magalzim.neoforum.service.AuthorService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class JWTUtil(
    private val authorService: AuthorService
) {

    @Value("\${jwt.secret}")
    private var secret: String = "2a12Dpr9yBjZksrrC34hnQEG1uDyF5HKckz3Cob4j5md1Jl3jXPF1ejzi"

    private val expiration: Long = 60000

    private val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

    fun generateToken(username: String, authorities: MutableCollection<out GrantedAuthority>): String {
        return Jwts
            .builder()
            .subject(username)
            .claim("role", authorities)
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(key)
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): UsernamePasswordAuthenticationToken {
        val username = Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(jwt)
            .payload
            .subject
        val user = authorService.loadUserByUsername(username.toString())
        return UsernamePasswordAuthenticationToken(username, null, user.authorities)
    }
}