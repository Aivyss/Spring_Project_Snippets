package com.example.springProjectSnippets.pointcutExample

import com.example.springProjectSnippets.pointcutExample.subPackage1.PointcutExample3Service
import com.example.springProjectSnippets.pointcutExample.subPackage2.PointcutExample4Service
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.aop.aspectj.AspectJExpressionPointcut
import java.lang.RuntimeException

class PointcutTest {

    @Test
    fun `pointcut resolves return type`() {
        val pointcut = AspectJExpressionPointcut() // Pointcut interface의 구현체

        // * when
        pointcut.expression = "execution(public int com.example.springProjectSnippets.pointcutExample.PointcutExample1Service.*(..))"

        // * then
        val clazz = PointcutExample1Service::class.java
        val pointcutTest1 = clazz.declaredMethods.find { it.name == "pointcutTestMethod1" } ?: throw RuntimeException()
        val pointcutTest2 = clazz.declaredMethods.find { it.name == "pointcutMethodTest2" } ?: throw RuntimeException()

        assertThat(pointcut.matches(pointcutTest1, clazz)).isTrue
        assertThat(pointcut.matches(pointcutTest2, clazz)).isFalse
    }

    @Test
    fun `pointcut can access private method`() {
        val pointcut = AspectJExpressionPointcut() // Pointcut interface의 구현체

        // * when
        pointcut.expression = "execution(private void com.example.springProjectSnippets.pointcutExample.PointcutExample1Service.*(..))"

        // * then
        val clazz = PointcutExample1Service::class.java
        val pointcutTest3 = clazz.declaredMethods.find { it.name == "pointcutTestMethod3" } ?: throw RuntimeException()

        assertThat(pointcut.matches(pointcutTest3, clazz)).isTrue
    }

    @Test
    fun `you can select multiple classes`() {
        val pointcut = AspectJExpressionPointcut() // Pointcut interface의 구현체

        // * when
        pointcut.expression = "execution(public int com.example.springProjectSnippets.pointcutExample.*.*(..))"

        // * then
        val clazz1 = PointcutExample1Service::class.java
        val clazz2 = PointcutExample2Service::class.java
        val clazz1Method1 = clazz1.declaredMethods.find { it.name == "pointcutTestMethod1" } ?: throw RuntimeException()
        val clazz2Method1 = clazz2.declaredMethods.find { it.name == "pointcutTestMethod1" } ?: throw RuntimeException()

        assertThat(pointcut.matches(clazz1Method1, clazz1)).isTrue
        assertThat(pointcut.matches(clazz2Method1, clazz2)).isTrue
    }

    @Test
    fun `you can select multiple classes in sub packages`() {
        val pointcut = AspectJExpressionPointcut() // Pointcut interface의 구현체

        // * when
        pointcut.expression = "execution(public int com.example.springProjectSnippets.pointcutExample..*.*(..))"

        val clazz1 = PointcutExample3Service::class.java
        val clazz2 = PointcutExample4Service::class.java
        val clazz3 = PointcutExample1Service::class.java
        val clazz4 = PointcutExample2Service::class.java

        // * then
        val clazz1Method1 = clazz1.declaredMethods.find { it.name == "pointcutTestMethod1" } ?: throw RuntimeException()
        val clazz2Method1 = clazz2.declaredMethods.find { it.name == "pointcutTestMethod1" } ?: throw RuntimeException()
        val clazz3Method1 = clazz2.declaredMethods.find { it.name == "pointcutTestMethod1" } ?: throw RuntimeException()
        val clazz4Method1 = clazz2.declaredMethods.find { it.name == "pointcutTestMethod1" } ?: throw RuntimeException()

        assertThat(pointcut.matches(clazz1Method1, clazz1)).isTrue
        assertThat(pointcut.matches(clazz2Method1, clazz2)).isTrue
        assertThat(pointcut.matches(clazz3Method1, clazz3)).isTrue
        assertThat(pointcut.matches(clazz4Method1, clazz4)).isTrue
    }
}