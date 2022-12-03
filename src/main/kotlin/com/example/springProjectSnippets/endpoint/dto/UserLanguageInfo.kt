package com.example.springProjectSnippets.endpoint.dto

import com.example.springProjectSnippets.api.http.SupportLanguage
import java.util.Locale

data class UserLanguageInfo(
    val languageId: SupportLanguage,
    val locale: Locale,
)