package com.example.springProjectSnippets.api.security.dto

import com.example.springProjectSnippets.api.role.Role
import com.example.springProjectSnippets.domain.User

class PrincipalDto(
    val userKey: Long,
    val roles: List<Role>,
) {
    companion object {
        fun from(user: User): PrincipalDto = user.let {
            PrincipalDto(
                userKey = it.id,
                roles = it.roles.map { entity -> entity.role },
            )
        }
    }
}