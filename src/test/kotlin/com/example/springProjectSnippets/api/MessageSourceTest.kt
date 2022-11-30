package com.example.springProjectSnippets.api

import com.example.springProjectSnippets.AbstractTest
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

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