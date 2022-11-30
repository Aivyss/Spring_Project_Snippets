package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.ExceptionController
import com.example.springProjectSnippets.api.RequestContext
import com.example.springProjectSnippets.api.config.MessageSourceConfig
import com.example.springProjectSnippets.application.UserAuthService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.MessageSource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@WebMvcTest(
    value = [
        UserAuthController::class,
        UserRetrieveController::class,
        ExceptionController::class,
        MessageSourceConfig::class,
        MessageSource::class,
        RequestContext::class,
    ],
    excludeAutoConfiguration = [
        SecurityAutoConfiguration::class,
    ]
)
open class EndpointTest {
    @MockBean
    protected lateinit var userAuthService: UserAuthService

    @Autowired
    protected lateinit var mockMvc: MockMvc

    @Autowired
    protected lateinit var objectMapper: ObjectMapper
}