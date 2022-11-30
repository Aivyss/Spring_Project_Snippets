package com.example.springProjectSnippets.api.config

import com.example.springProjectSnippets.api.http.SupportLanguage
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ReloadableResourceBundleMessageSource

/**
 * message source configuration
 *
 * @author Aivyss
 */
@Configuration
class MessageSourceConfig {
    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ReloadableResourceBundleMessageSource()
        messageSource.setBasenames("classpath:/messages/message")
        messageSource.setDefaultLocale(SupportLanguage.EN.locale)
        messageSource.setDefaultEncoding("UTF-8")
        messageSource.setCacheSeconds(60)

        return messageSource
    }
}