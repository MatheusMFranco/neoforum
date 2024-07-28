package br.com.magalzim.neoforum.config

import br.com.magalzim.neoforum.model.UserRoleAuthority
import br.com.magalzim.neoforum.security.JWTAuthenticationFilter
import br.com.magalzim.neoforum.security.JWTLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration (
    private val configuration: AuthenticationConfiguration,
    private val userDetailsService: UserDetailsService,
    private val jwtUtil: JWTUtil
){
    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers(HttpMethod.POST, "/authors").permitAll()
                    .requestMatchers(HttpMethod.POST, "/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/swagger-ui/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/v3/api-docs/**").permitAll()
                    .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()

                    .requestMatchers(HttpMethod.PUT, "/authors").hasAuthority(UserRoleAuthority.READ_AND_WRITE.name)
                    .requestMatchers(HttpMethod.GET, "/authors/{id}").hasAuthority(UserRoleAuthority.READ_AND_WRITE.name)
                    .requestMatchers("/forums").hasAuthority(UserRoleAuthority.READ_AND_WRITE.name)
                    .requestMatchers("/topics/**").hasAuthority(UserRoleAuthority.READ_AND_WRITE.name)
                    .requestMatchers("/answers/**").hasAuthority(UserRoleAuthority.READ_AND_WRITE.name)

                    .requestMatchers("/premium/**").hasAuthority(UserRoleAuthority.PREMIUM.name)

                    .requestMatchers("/monitor/**").hasAuthority(UserRoleAuthority.MONITOR.name)
                    .requestMatchers(HttpMethod.PUT, "/boards/**").hasAuthority(UserRoleAuthority.MONITOR.name)
                    .requestMatchers(HttpMethod.PUT, "/answers/**").hasAuthority(UserRoleAuthority.MONITOR.name)
                    .requestMatchers(HttpMethod.PUT, "/topics/**").hasAuthority(UserRoleAuthority.MONITOR.name)

                    .requestMatchers("/boards/**").hasAuthority(UserRoleAuthority.ADMIN.name)
                    .requestMatchers(HttpMethod.GET, "/roles/").hasAuthority(UserRoleAuthority.ADMIN.name)
                    .requestMatchers(HttpMethod.GET, "/authors/").hasAuthority(UserRoleAuthority.ADMIN.name)
                    .requestMatchers(HttpMethod.PUT, "/user-roles").hasAuthority(UserRoleAuthority.ADMIN.name)
                    .requestMatchers(HttpMethod.DELETE, "/answers/**").hasAuthority(UserRoleAuthority.ADMIN.name)
                    .requestMatchers(HttpMethod.DELETE, "/topics/**").hasAuthority(UserRoleAuthority.ADMIN.name)
                    .anyRequest().authenticated()
            }
            .addFilterBefore(JWTLoginFilter(authenticationManager = configuration.authenticationManager, jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .addFilterBefore(JWTAuthenticationFilter(jwtUtil = jwtUtil), UsernamePasswordAuthenticationFilter().javaClass)
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }.build()
    }

    @Bean
    fun bCryptPasswordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.userDetailsService(userDetailsService)?.passwordEncoder(bCryptPasswordEncoder())
    }

}