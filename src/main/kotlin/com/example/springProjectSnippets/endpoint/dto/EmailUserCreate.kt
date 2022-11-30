package com.example.springProjectSnippets.endpoint.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

/**
 * test dto
 *
 * @author Aivyss
 * @since 11/30/2022
 */
class EmailUserCreate(
    @field:Email(message = "EXCEPTION.PARAMETERS.EMAIL_FORMAT")
    val email: String,
    @field:NotBlank(message = "EXCEPTION.PARAMETERS.NOT_BLANK")
    val password: String,
    @field:NotBlank(message = "EXCEPTION.PARAMETERS.NOT_BLANK")
    val username: String,
)