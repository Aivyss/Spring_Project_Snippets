package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.http.SuccessResponse
import com.example.springProjectSnippets.application.UserLanguageService
import com.example.springProjectSnippets.endpoint.dto.UserLanguageInfo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users/{userKey}/lang")
class UserLanguageController(
    private val service: UserLanguageService,
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun retrieveLanguageSetting(@PathVariable userKey: Long): SuccessResponse<UserLanguageInfo> {
        val result = service.retrieveUserLanguage()

        return SuccessResponse(data = result, httpStatus = HttpStatus.OK)
    }
}