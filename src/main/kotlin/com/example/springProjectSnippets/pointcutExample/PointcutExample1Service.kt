package com.example.springProjectSnippets.pointcutExample

import org.springframework.stereotype.Service

@Service
class PointcutExample1Service {
    fun pointcutTestMethod1(): Int = 1
    fun pointcutMethodTest2(): String = "2"
    private fun pointcutTestMethod3() {

    }
}