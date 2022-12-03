package com.example.springProjectSnippets.api.locale

import org.springframework.stereotype.Component
import org.springframework.web.servlet.LocaleResolver
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * locale change interceptor
 *
 * @author Aivyss
 * @since 12/04/2022
 */
@Component
class LocaleChangeInterceptor(
    val localeResolver: LocaleResolver
): org.springframework.web.servlet.i18n.LocaleChangeInterceptor() {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val locale = localeResolver.resolveLocale(request)
        localeResolver.setLocale(request, response, locale)

        return true
    }
}