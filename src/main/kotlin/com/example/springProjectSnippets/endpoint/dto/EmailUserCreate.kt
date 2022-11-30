package com.example.springProjectSnippets.endpoint.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

/**
 * test dto
 *
 * @author Aivyss
 * @since 11/30/2022
 */
data class EmailUserCreate(
    @Email(message = "EXCEPTION.PARAMETERS.EMAIL_FORMAT")
    val email: String,
    @NotBlank(message = "EXCEPTION.PARAMETERS.NOT_BLANK")
    val password: String,
)