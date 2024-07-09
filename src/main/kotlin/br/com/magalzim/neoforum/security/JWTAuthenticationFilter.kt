package br.com.magalzim.neoforum.security

import br.com.magalzim.neoforum.config.JWTUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(private val jwtUtil: JWTUtil) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)
        if(jwtUtil.isValid(jwt)){
            val authentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?): String? {
        return token.let { jwt ->
            jwt?.startsWith("Bearer ")
            jwt?.substring(7, jwt.length)
        }
    }

}
