package com.example.springProjectSnippets.api

import org.springframework.context.annotation.Scope
import org.springframework.context.annotation.ScopedProxyMode
import org.springframework.stereotype.Component
import java.util.*

/**
 * request context (isolation level: thread => life cycle: request)
 *
 * @author Aivyss
 * @since 11/30/2022
 */
@Component
@Scope("request", proxyMode = ScopedProxyMode.TARGET_CLASS)
class RequestContext(
    val supportLanguage: SupportLanguage = SupportLanguage.KO,
)