package com.example.springProjectSnippets.application

import com.example.springProjectSnippets.api.exception.ErrorCode
import com.example.springProjectSnippets.api.exception.InvalidRequestExceptionBuilder.throwInvalidRequest
import com.example.springProjectSnippets.domain.UserFactory
import com.example.springProjectSnippets.domain.UserRepository
import com.example.springProjectSnippets.endpoint.dto.EmailUserCreate
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

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
}