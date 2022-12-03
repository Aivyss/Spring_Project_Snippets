package com.example.springProjectSnippets.application

import com.example.springProjectSnippets.api.http.RequestContext
import com.example.springProjectSnippets.domain.UserLanguageRepository
import com.example.springProjectSnippets.domain.getLangById
import com.example.springProjectSnippets.endpoint.dto.UserLanguageInfo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserLanguageService (
    private val userLanguageRepository: UserLanguageRepository,
    private val requestContext: RequestContext,
){
    fun retrieveUserLanguage(): UserLanguageInfo {
        return userLanguageRepository.getLangById(requestContext.userKey).let {
            UserLanguageInfo(
                languageId = it.supportLanguage,
                locale = it.supportLanguage.locale
            )
        }
    }
}