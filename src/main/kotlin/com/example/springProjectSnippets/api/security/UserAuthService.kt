package com.example.springProjectSnippets.api.security

import com.example.springProjectSnippets.api.exception.ErrorCode
import com.example.springProjectSnippets.api.exception.InvalidRequestExceptionBuilder.invalidRequest
import com.example.springProjectSnippets.api.exception.InvalidRequestExceptionBuilder.throwInvalidRequest
import com.example.springProjectSnippets.api.security.dto.CustomAuthentication
import com.example.springProjectSnippets.api.security.dto.PrincipalDetails
import com.example.springProjectSnippets.domain.UserFactory
import com.example.springProjectSnippets.domain.UserRepository
import com.example.springProjectSnippets.endpoint.dto.EmailUserCreate
import com.example.springProjectSnippets.endpoint.dto.EmailUserLogin
import com.example.springProjectSnippets.endpoint.dto.LoginSuccessUser
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.servlet.http.HttpServletResponse

/**
 * User Auth Test Service Bean
 *
 * @author Aivyss
 * @since 12/01/2022
 */
@Service
class UserAuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
) {
    fun signup(request: EmailUserCreate): Long {
        if (userRepository.existsByEmailAndDeletedFalse(request.email)) {
            throwInvalidRequest(
                errorCode = ErrorCode.SIGN_UP_DUPLICATE_EMAIL_ERROR,
                debugMessage = "duplicated email",
            )
        }

        val userEntity = request.let {
            UserFactory.create(
                email = it.email,
                password = passwordEncoder.encode(request.password),
                username = it.username
            )
        }

        return userRepository.save(userEntity).id
    }

    fun emailUserLogin(request: EmailUserLogin, response: HttpServletResponse): LoginSuccessUser {
        val user = userRepository.findByEmailAndDeletedFalse(request.email)
            ?: throw invalidRequest(
                errorCode = ErrorCode.NO_SUCH_USER_LOGIN_ERROR,
                debugMessage = "no such user by email login",
            )

        val dbPassword = user.password

        if (!passwordEncoder.matches(request.password, dbPassword)) {
            throw invalidRequest(
                errorCode = ErrorCode.NOT_MATCHES_PASSWORD_LOGIN_ERROR,
                debugMessage = "no same password",
            )
        }

        val authentication = CustomAuthentication(PrincipalDetails(
            userKey = user.id,
            roles = user.roles.map { it.role }
        ), true)
        SecurityContextHolder.getContext().authentication = authentication
        val responseDto = LoginSuccessUser(
            userKey = user.id,
            accessToken = jwtService.generateAccessToken(user),
            refreshToken = jwtService.generateRefreshToken(user),
            roles = user.roles.map { it.role }
        )
        response.setHeader("Authorization", "Bearer ${responseDto.accessToken}")
        response.setHeader("refresh", "Bearer ${responseDto.refreshToken}")

        return responseDto
    }
}