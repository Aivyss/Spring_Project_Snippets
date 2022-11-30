package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.ObjectMapperHolder
import com.example.springProjectSnippets.api.exception.ParameterErrorCode
import com.example.springProjectSnippets.endpoint.dto.EmailUserCreate
import com.example.springProjectSnippets.api.http.FailResponse
import com.example.springProjectSnippets.api.http.SuccessResponse
import com.fasterxml.jackson.core.type.TypeReference
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.mockito.Mockito.doReturn
import org.mockito.kotlin.any
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

/**
 * MockMvc Test Example
 *
 * @author Aivyss
 * @since 12/01/2022
 */
class UserAuthControllerTest : EndpointTest() {
    val uri = "/api/users/auth"

    @Test
    fun `Should Success to signup`() {
        // * given
        val request = EmailUserCreate(
            email = "test@test.io",
            password = "test_password",
            username = "test_username"
        )
        doReturn(1L).`when`(userAuthService).signup(any())

        // * when
        val requestBuilder = MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(ObjectMapperHolder.writeValueAsString(request))
        val perform = mockMvc.perform(requestBuilder)

        // * then
        val responseJson = perform
            .andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.CREATED.value()))
            .andReturn().response.contentAsString

        val response = ObjectMapperHolder.readValue(responseJson, object : TypeReference<SuccessResponse<Boolean>>() {})
        assertThat(response.data).isTrue
    }

    @Test
    fun `Should fail to signup(invalid email format)`() {
        // * given
        val request = EmailUserCreate(
            email = "invalid email format",
            password = "test_password",
            username = "test_username"
        )

        // * when
        val requestBuilder = MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(ObjectMapperHolder.writeValueAsString(request))
        val perform = mockMvc.perform(requestBuilder)

        // * then
        val responseJson = perform
            .andExpect(MockMvcResultMatchers.status().`is`(HttpStatus.BAD_REQUEST.value()))
            .andReturn().response.contentAsString

        val response = ObjectMapperHolder.readValue(responseJson, object : TypeReference<FailResponse>() {})
        assertThat(response.errorCode).isEqualTo(ParameterErrorCode.EMAIL_FORMAT.getNameValue())
    }
}