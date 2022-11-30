package com.example.springProjectSnippets.api

/**
 * i18n localeMessage Code based on exception for invalid data processing
 *
 * @author Aivyss
 * @since 11/30/2022
 */
enum class ErrorCode : CodeEnum {
    INTERNAL_SERVER_ERROR,
    SIGN_UP_DUPLICATE_EMAIL_ERROR,
    ;

    override fun getCodeValue(): String = "EXCEPTION.$name"
    override fun getNameValue(): String = this.name

    companion object {
        fun matches(str: String?): CodeEnum = ErrorCode.values().find { it.getCodeValue() == str } ?: INTERNAL_SERVER_ERROR
    }
}

/**
 * i18n localeMessage Code based on validation fail exception for validation of request body
 *
 *  @author Aivyss
 *  @since 11/30/2022
 */
enum class ParameterErrorCode : CodeEnum {
    EMAIL_FORMAT,
    ;

    override fun getCodeValue(): String = "EXCEPTION.PARAMETERS.${name}"
    override fun getNameValue(): String = this.name

    companion object {
        fun matches(str: String?): CodeEnum = ParameterErrorCode.values().find { it.getCodeValue() == str } ?: ErrorCode.INTERNAL_SERVER_ERROR
    }
}
