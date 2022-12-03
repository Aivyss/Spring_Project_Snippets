package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.config.I18nConfig
import com.example.springProjectSnippets.api.exception.ExceptionController
import com.example.springProjectSnippets.api.http.RequestContext
import com.example.springProjectSnippets.api.security.UserAuthService
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.MessageSource
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

/**
 * MockMvcTest Example
 *
 * @author Aivyss
 * @since 12/01/2022
 */
@ActiveProfiles("test")
@ExtendWith(SpringExtension::class)
@WebMvcTest(
    value = [
        UserAuthController::class,
        UserRetrieveController::class,
        ExceptionController::class,
        I18nConfig::class,
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
}