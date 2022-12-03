package com.example.springProjectSnippets.api.http

import java.util.*

/**
 * Supported Languages
 *
 * @author Aivyss
 * @since 11/30/2022
 */
enum class SupportLanguage(val locale: Locale, val languageId: String) {
    KO(Locale.forLanguageTag("ko"), "ko"),
    EN(Locale.forLanguageTag("en"), "en"),
    JP(Locale.forLanguageTag("ja"), "ja"),
    ;

    companion object {
        fun matches(languageId: String?): SupportLanguage {
            return try {
                SupportLanguage.valueOf(languageId?.uppercase() ?: EN.name)
            } catch (e: Exception) {
                SupportLanguage.values().firstOrNull { it.languageId == languageId } ?: EN
            }
        }
    }
}