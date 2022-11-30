package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.endpoint.dto.EmailUserCreate
import com.example.springProjectSnippets.exception.SuccessResponse
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * test endpoints
 *
 * @author Aivyss
 * @since 11/30/2022
 */
@RestController
@RequestMapping("/api/users/auth")
class UserAuthController {
    @PostMapping
    fun signup(@RequestBody @Validated request: EmailUserCreate): SuccessResponse<EmailUserCreate> {
        return SuccessResponse(request)
    }
}