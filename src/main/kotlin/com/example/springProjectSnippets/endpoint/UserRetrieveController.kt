package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.http.SuccessResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * test endpoints
 *
 * @author Aivyss
 * @since 11/30/2022
 */
@RestController
@RequestMapping("/api/users")
class UserRetrieveController {
    @GetMapping("/info")
    fun retrieveUserInfo() = SuccessResponse("OK")
}