package com.example.springProjectSnippets

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

/**
 * application loading point
 *
 * @author Aivyss
 * @since 11/30/2022
 */
@ConfigurationPropertiesScan
@SpringBootApplication
class SpringProjectSnippets

fun main(args: Array<String>) {
    runApplication<SpringProjectSnippets>(*args)
}
