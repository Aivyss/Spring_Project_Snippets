package com.example.springProjectSnippets.exception

import com.example.springProjectSnippets.api.ErrorCode
import com.example.springProjectSnippets.api.InvalidRequestException
import com.example.springProjectSnippets.api.SupportLanguage
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

/**
 * base response
 * restricted sub types: SuccessResponse, FailResponse
 *
 * @author Aivyss
 */
sealed class BaseResponse(
    val success: Boolean,
    var httpStatus: HttpStatus,
    var supportLanguage: SupportLanguage = SupportLanguage.KO,
    var responseTime: LocalDateTime = LocalDateTime.now(),
)

/**
 * Success Response
 *
 * @author Aivyss
 * @since 11/30/2022
 */
class SuccessResponse<T>(
    val data: T,
    httpStatus: HttpStatus = HttpStatus.OK,
) : BaseResponse(
    success = true,
    httpStatus = httpStatus,
)

/**
 * response for invalid request
 *
 * @author Aivyss
 */
class FailResponse(
    val errorCode: String = ErrorCode.INTERNAL_SERVER_ERROR.getNameValue(),
    val interfaceMessage: String,
    supportLanguage: SupportLanguage,
    responseTime: LocalDateTime,
    httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
) : BaseResponse(
    success = false,
    httpStatus = httpStatus,
    supportLanguage = supportLanguage,
    responseTime = responseTime,
)

object SuccessResponseFactory {
    fun <T> create(
        data: T,
    ) = SuccessResponse(data)
}

object FailResponseFactory {
    fun create(
        e: InvalidRequestException,
        localeMessage: String,
        httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
        supportLanguage: SupportLanguage,
        now: LocalDateTime,
    ): FailResponse {
        return FailResponse(
            errorCode = e.errorCode.getNameValue(),
            interfaceMessage = localeMessage,
            supportLanguage = supportLanguage,
            responseTime = now,
            httpStatus = httpStatus
        )
    }

    fun create(
        errorCode: String = ErrorCode.INTERNAL_SERVER_ERROR.getNameValue(),
        localeMessage: String,
        httpStatus: HttpStatus = HttpStatus.BAD_REQUEST,
        supportLanguage: SupportLanguage,
        now: LocalDateTime,
    ): FailResponse {
        return FailResponse(
            errorCode = errorCode,
            interfaceMessage = localeMessage,
            supportLanguage = supportLanguage,
            responseTime = now,
            httpStatus = httpStatus
        )
    }
}