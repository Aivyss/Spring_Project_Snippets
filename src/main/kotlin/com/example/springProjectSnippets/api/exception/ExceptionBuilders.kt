package com.example.springProjectSnippets.api.exception

import com.example.springProjectSnippets.api.CodeEnum
import org.springframework.http.HttpStatus

object InvalidRequestExceptionBuilder {
    fun throwInvalidRequest(
        errorCode: CodeEnum,
        debugMessage: String,
        messageArgs: Array<String> = emptyArray(),
        httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
    ): Nothing {
        throw InvalidRequestException(
            errorCode = errorCode,
            messageArgs = messageArgs,
            debugMessage = debugMessage,
            httpStatus = httpStatus,
        )
    }
}