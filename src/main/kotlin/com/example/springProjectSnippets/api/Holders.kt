package com.example.springProjectSnippets.api

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

/**
 * ObjectMapperHolder for Kotlin environment
 *
 * @author Aivyss
 * @since 12/01/2022
 */
object ObjectMapperHolder {
    val objectMapper: ObjectMapper = ObjectMapper().registerModule(JavaTimeModule()).registerKotlinModule()

    fun writeValueAsString(data: Any) = objectMapper.writeValueAsString(data)
    fun <T> readValue(json: String, type: TypeReference<T>): T = objectMapper.readValue(json, type)
}