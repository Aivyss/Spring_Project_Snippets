package com.example.springProjectSnippets.reactor

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

class MonoFluxTest {
    @Test
    fun `create some flux`() {
        val basket1 = listOf("orange", "banana", "apple", "orange")
        val basket2 = listOf("lemon", "grape", "strawberry", "lemon")
        val basket3 = listOf("banana", "grape")


        val result = mutableListOf<FruitInfo>()
        /**
         * 값을 꺼내 새로운 퍼블리셔로 만드는 메소드 flatMap, flatMapSequential, concatMap
         * 비동기로 동작시에 순서를 보장하지 않는 메소드: flatMap
         */
        Flux.fromIterable(listOf(basket1, basket2, basket3)).concatMap { basket ->
            val distinctFruits = Flux.fromIterable(basket).distinct().collectList()
            val countFruitsMono = Flux.fromIterable(basket)
                .groupBy { it }
                .concatMap { groupFlux -> groupFlux.count().map { count -> mutableMapOf(groupFlux.key() to count) } }
                .reduce { prev, curr ->
                    prev.putAll(curr)

                    prev
                }

            Flux.zip(distinctFruits, countFruitsMono) {distinct, count-> FruitInfo(distinct, count)}
        }.subscribe { result += it }

        println(result)
    }

    @Test
    fun `parallel scheduling`() {
        val basket1 = listOf("orange", "banana", "apple", "orange")
        val basket2 = listOf("lemon", "grape", "strawberry", "lemon")
        val basket3 = listOf("banana", "grape")

        /**
         * 값을 꺼내 새로운 퍼블리셔로 만드는 메소드 flatMap, flatMapSequential, concatMap
         * 비동기로 동작시에 순서를 보장하지 않는 메소드: flatMap
         */
        Flux.fromIterable(listOf(basket1, basket2, basket3)).concatMap { basket ->
            val distinctFruits = Flux.fromIterable(basket).distinct().collectList().doOnSubscribe { Schedulers.parallel() }
            val countFruitsMono = Flux.fromIterable(basket)
                .groupBy { it }
                .concatMap { groupFlux -> groupFlux.count().map { count -> mutableMapOf(groupFlux.key() to count) } }
                .reduce { prev, curr ->
                    prev.putAll(curr)

                    prev
                }.doOnSubscribe { Schedulers.parallel() }

            Flux.zip(distinctFruits, countFruitsMono) {distinct, count-> FruitInfo(distinct, count)}
        }.subscribe { println(it) }
    }
}


data class FruitInfo(
    val distinctFruits: List<String>,
    val countFruits: Map<String, Long>
)