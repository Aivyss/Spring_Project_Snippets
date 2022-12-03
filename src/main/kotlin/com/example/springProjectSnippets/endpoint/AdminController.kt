package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.http.RequestContext
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/admin/{userKey}")
class AdminController(
    private val requestContext: RequestContext
) {
    @GetMapping("/tickets")
    fun paginateTickets(@PathVariable userKey: Long) {
        println(userKey)
    }
}