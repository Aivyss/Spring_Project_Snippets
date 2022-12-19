package com.example.springProjectSnippets.serializer

import com.fasterxml.jackson.databind.annotation.JsonSerialize
import javax.validation.constraints.NotNull

@JsonSerialize(using = SerializerTestDtoSerializer::class)
class SerializerTestDto(
    val name: String,
    @field:NotNull
    val age: Int,
)