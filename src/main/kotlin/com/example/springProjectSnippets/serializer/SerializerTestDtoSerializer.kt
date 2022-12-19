package com.example.springProjectSnippets.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.time.LocalDateTime

class SerializerTestDtoSerializer : JsonSerializer<SerializerTestDto>() {

    override fun serialize(value: SerializerTestDto?, gen: JsonGenerator, serializers: SerializerProvider) {
        gen.writeStartObject()

        gen.writeStringField("name", value?.name)
        gen.writeNumberField("age", value?.age ?: -1)
        gen.writeStringField("requestTime", LocalDateTime.now().toString())

        gen.writeEndObject()
    }
}