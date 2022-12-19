package com.example.springProjectSnippets.serializer

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class SerializerTestController {

    @PostMapping("/api/test/serializer")
    fun testDeserialize(@RequestBody request: SerializerTestDto?): SerializerTestDto? = request
}