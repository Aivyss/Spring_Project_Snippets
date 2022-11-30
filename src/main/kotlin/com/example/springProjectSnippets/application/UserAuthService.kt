package com.example.springProjectSnippets.application

import com.example.springProjectSnippets.api.ErrorCode
import com.example.springProjectSnippets.api.InvalidRequestExceptionBuilder.throwInvalidRequest
import com.example.springProjectSnippets.api.domain.UserFactory
import com.example.springProjectSnippets.api.domain.UserRepository
import com.example.springProjectSnippets.endpoint.dto.EmailUserCreate
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAuthService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
) {
    fun signup(request: EmailUserCreate): Long {
        if (userRepository.existsByEmail(request.email)) {
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