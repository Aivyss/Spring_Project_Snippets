package com.example.springProjectSnippets.api.jwt

import com.example.springProjectSnippets.api.TimeService
import com.example.springProjectSnippets.api.config.JwtProperty
import com.example.springProjectSnippets.api.security.dto.PrincipalDto
import com.example.springProjectSnippets.domain.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

/**
 * Jwt Service
 *
 * @author Aivyss
 * @since 12/02/2022
 */
@Service
class JwtService(
    private val jwtProperty: JwtProperty,
    private val timeService: TimeService,
) {
    fun generateAccessToken(user: User): String {
        val principalDto = PrincipalDto.from(user)
        val now = Date.from(timeService.now().toInstant())
        val expireTime = Date(now.time + jwtProperty.accessTokenLifetime)

        return Jwts.builder()
            .setSubject(principalDto.toString())
            .setIssuedAt(now)
            .setExpiration(expireTime)
            .signWith(SignatureAlgorithm.HS512, jwtProperty.accessTokenSecret)
            .compact()
    }

    fun generateRefreshToken(user: User): String {
        val now = Date.from(timeService.now().toInstant())
        val expireTime = Date(now.time + jwtProperty.refreshTokenLifetime)

        return Jwts.builder()
            .setSubject(user.id.toString())
            .setIssuedAt(Date.from(timeService.now().toInstant()))
            .setIssuedAt(now)
            .setExpiration(expireTime)
            .signWith(SignatureAlgorithm.HS512, jwtProperty.refreshTokenSecret)
            .compact()
    }
}