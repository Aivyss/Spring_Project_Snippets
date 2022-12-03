package com.example.springProjectSnippets.api.config

import org.springframework.web.WebApplicationInitializer
import org.springframework.web.filter.CharacterEncodingFilter
import javax.servlet.ServletContext


/**
 * charater encoding configuration
 * 사실 스프링 부트에서는 default character encoding이 UTF-8이라 거의 적용 안해도 문제는 없음.
 *
 * @author Aivyss
 * @since 12/03/2022
 */
class ApplicationInitializer: WebApplicationInitializer {
    override fun onStartup(servletContext: ServletContext) {
        val charEncodingFilterRegistration = servletContext.addFilter("CharacterEncodingFilter", CharacterEncodingFilter::class.java)
        charEncodingFilterRegistration.setInitParameter("encoding", "UTF-8")
        charEncodingFilterRegistration.setInitParameter("forceEncoding", "true")
        charEncodingFilterRegistration.addMappingForUrlPatterns(null, true, "/*")
    }
}