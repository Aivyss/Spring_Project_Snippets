package com.example.springProjectSnippets.api.logger

/**
 * Annotation for LoggerAspect(HttpRequest Logging)
 * @author Aivyss
 * @since 12/01/2022
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(allowedTargets = [AnnotationTarget.CLASS, AnnotationTarget.FUNCTION])
annotation class Log
