package com.example.springProjectSnippets.api.security

import com.example.springProjectSnippets.api.ObjectMapperHolder
import com.example.springProjectSnippets.api.exception.ExceptionController
import com.example.springProjectSnippets.api.exception.InvalidRequestException
import com.example.springProjectSnippets.api.http.RequestContext
import com.example.springProjectSnippets.api.security.dto.JwtAuthExcludeUrlPattern
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Jwt Authorization Filter
 *
 * @author Aivyss
 * @since 12/03/2022
 */
@Component
class JwtAuthFilter(
    val jwtService: JwtService,
    val exceptionController: ExceptionController,
    val requestContext: RequestContext,
) : OncePerRequestFilter() {
    private val excludeUrlPatterns: List<String> = JwtAuthExcludeUrlPattern.values().map { it.url }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val accessToken = request.getHeader("Authorization").replace("Bearer ", "")
            val principalDetails = jwtService.parseAccessToken(accessToken).also {
                requestContext.roles = it.roles
                requestContext.userKey = it.userKey
                requestContext.validUser = true
            }
            val authentication = principalDetails.createAuthentication()
            SecurityContextHolder.getContext().authentication = authentication

            filterChain.doFilter(request, response)
        } catch (e: InvalidRequestException) {
            response.contentType = "application/json"
            response.characterEncoding = "UTF-8"
            response.writer.write(ObjectMapperHolder.writeValueAsString(exceptionController.invalidRequestException(e)))
        }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.requestURI in excludeUrlPatterns
    }
}