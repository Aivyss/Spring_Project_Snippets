package com.example.springProjectSnippets.api.locale

import com.example.springProjectSnippets.api.http.BaseResponse
import com.example.springProjectSnippets.api.http.FailResponse
import com.example.springProjectSnippets.api.http.RequestContext
import com.example.springProjectSnippets.api.http.SuccessResponse
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice

@Aspect
@Component
@Order(1)
@RestController
@RestControllerAdvice
class ResponseLanguageAspect(
    private val requestContext: RequestContext,
) {

    @Around("@within(org.springframework.web.bind.annotation.RestController)" + "||" +
            "@within(org.springframework.web.bind.annotation.RestControllerAdvice)")
    fun point(point: ProceedingJoinPoint): Any? {
        return point.proceed().apply {
            if (this is BaseResponse) {
                this.supportLanguage = requestContext.supportLanguage
            }
        }
    }
}