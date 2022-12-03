package com.example.springProjectSnippets.api.security.dto

import com.example.springProjectSnippets.api.role.Role
import org.springframework.security.core.GrantedAuthority

class CustomAuthority(val role: Role) : GrantedAuthority {
    override fun getAuthority(): String = role.name
}