package com.example.springProjectSnippets.api.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

/**
 * settings for Spring Security config
 *
 * @author Aivyss
 * @since 11/30/2022
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfig(private val domains: CorsDomainProperty) {

    /**
     * 특정 role을 가진 사람에게 허용되는 url pattern 등록
     */
    @Bean
    fun configure(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            .authorizeHttpRequests().antMatchers("/**").hasRole("USER")

        return httpSecurity.build()
    }

    /**
     * scurity filter chain을 거치지 않는 url 패턴 등록
     */
    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer? {
        return WebSecurityCustomizer { web: WebSecurity -> web.ignoring().antMatchers("/api/users/auth") }
    }

    /**
     * cors 접속 허용 제한
     */
    @Bean
    fun corsWebFilter(): CorsFilter {
        val corsConfig = CorsConfiguration().apply {
            allowedOrigins =
                listOf(
                    domains.localhost,
                )
            allowCredentials = true
            maxAge = 8000L
            addAllowedMethod("*")
            addAllowedHeader("*")
            addExposedHeader(HttpHeaders.CONTENT_DISPOSITION)
        }
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return CorsFilter(source)
    }

    /**
     * password encryptor
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}