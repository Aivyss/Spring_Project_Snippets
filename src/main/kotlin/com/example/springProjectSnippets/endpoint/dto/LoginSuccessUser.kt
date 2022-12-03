package com.example.springProjectSnippets.endpoint.dto

import com.example.springProjectSnippets.api.role.Role

data class LoginSuccessUser(
    val userKey: Long,
    val accessToken: String,
    val refreshToken: String,
    val roles: List<Role>
)