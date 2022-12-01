package com.example.springProjectSnippets.endpoint.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

data class EmailUserLogin(
    @field:Email(message = "EXCEPTION.PARAMETERS.EMAIL_FORMAT")
    @field:NotBlank(message = "EXCEPTION.PARAMETERS.NOT_BLANK")
    val email: String,

    @field:NotBlank(message = "EXCEPTION.PARAMETERS.NOT_BLANK")
    val password: String,
)