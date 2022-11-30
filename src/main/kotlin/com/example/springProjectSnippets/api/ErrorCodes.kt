package com.example.springProjectSnippets.api

/**
 * i18n localeMessage Code based on exception for invalid data processing
 *
 * @author Aivyss
 */
enum class ErrorCode(private val code: String) : CodeEnum {
    INTERNAL_SERVER_ERROR("EXCEPTION.INTERNAL_SERVER_ERROR"),
    ;

    override fun getCodeValue(): String = this.code

    companion object {
        fun matches(str: String?): CodeEnum = ErrorCode.values().find { it.name == str } ?: INTERNAL_SERVER_ERROR
    }
}

/**
 * i18n localeMessage Code based on validation fail exception for validation of request body
 *
 *  @author Aivyss
 *  @since 11/30/2022
 */
enum class ParameterErrorCode(private val code: String) : CodeEnum {
    EMAIL_FORMAT("EXCEPTION.PARAMETERS.EMAIL_FORMAT"),
    ;

    override fun getCodeValue(): String  = this.code

    companion object {
        fun matches(str: String?): CodeEnum = ParameterErrorCode.values().find { it.name == str } ?: ErrorCode.INTERNAL_SERVER_ERROR
    }
}
