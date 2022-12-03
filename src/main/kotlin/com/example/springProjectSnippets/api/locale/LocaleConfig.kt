package com.example.springProjectSnippets.api.locale

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * register locale change interceptor
 *
 * @author Aivyss
 * @since 12/04/2022
 */
@Configuration
class LocaleConfig(
    private val interceptor: LocaleChangeInterceptor,
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(interceptor)
    }
}