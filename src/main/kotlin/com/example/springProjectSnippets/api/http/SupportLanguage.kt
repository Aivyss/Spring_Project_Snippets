package com.example.springProjectSnippets.api.http

import java.util.*

/**
 * Supported Languages
 *
 * @author Aivyss
 * @since 11/30/2022
 */
enum class SupportLanguage(val locale: Locale) {
    KO(Locale.forLanguageTag("ko")),
    EN(Locale.forLanguageTag("en")),
    JP(Locale.forLanguageTag("ja")),
    ;
}