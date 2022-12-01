package com.example.springProjectSnippets.api.security

import com.example.springProjectSnippets.api.security.dto.PrincipalDto
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

class CustomAuthentication(
    private val principal: PrincipalDto,
    private var authenticated: Boolean,
) : Authentication {
    override fun getName(): String = "we do not use"

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = principal.roles.map { CustomAuthority(it) }
        val mutable = mutableListOf<GrantedAuthority>()

        for (authority in authorities) {
            mutable += authorities
        }
        return mutable
    }

    override fun getCredentials() = "we do not use"

    override fun getDetails(): Any = "we do not use"

    override fun getPrincipal(): PrincipalDto  = principal

    override fun isAuthenticated(): Boolean = authenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.authenticated = isAuthenticated
    }
}