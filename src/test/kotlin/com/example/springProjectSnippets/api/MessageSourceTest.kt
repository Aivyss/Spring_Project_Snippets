package com.example.springProjectSnippets.api

import com.example.springProjectSnippets.AbstractTest
import com.example.springProjectSnippets.api.exception.ErrorCode
import com.example.springProjectSnippets.api.http.SupportLanguage
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Message Source Test & AssertJ Example
 *
 * @author Aivyss
 * @since 11/30/2022
 */
class MessageSourceTest : AbstractTest() {
    @Test
    fun `Should Success to localize message`() {
        // * given
        val locale = SupportLanguage.JP.locale

        // * when
        val message = messageSource.getMessage(ErrorCode.INTERNAL_SERVER_ERROR.getCodeValue(), emptyArray(), locale)

        // * then
        assertThat(message).isEqualTo("原因不明のエラー")
    }
}