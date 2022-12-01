package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.security.UserAuthService
import com.example.springProjectSnippets.endpoint.dto.EmailUserCreate
import com.example.springProjectSnippets.api.http.SuccessResponse
import com.example.springProjectSnippets.api.logger.Log
import com.example.springProjectSnippets.endpoint.dto.EmailUserLogin
import com.example.springProjectSnippets.endpoint.dto.LoginSuccessUser
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

/**
 * test endpoints
 *
 * @author Aivyss
 * @since 11/30/2022
 */
@RestController
@RequestMapping("/api/users/auth")
@Log
class UserAuthController(
    private val service: UserAuthService,
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun signup(@RequestBody @Valid request: EmailUserCreate): SuccessResponse<Boolean> {
        service.signup(request)

        return SuccessResponse(data = true, httpStatus = HttpStatus.CREATED)
    }

    @PostMapping("/login", params=["type=EMAIL"])
    @ResponseStatus(HttpStatus.OK)
    fun login(
        @RequestBody @Valid request: EmailUserLogin
    ): SuccessResponse<LoginSuccessUser> = SuccessResponse(
        data = service.emailUserLogin(request),
        httpStatus = HttpStatus.OK,
    )
}