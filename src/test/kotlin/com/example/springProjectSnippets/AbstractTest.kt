package com.example.springProjectSnippets

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

/**
 * Super Class Example for Test Classes
 *
 * @author Aivyss
 * @since 12/01/2022
 */
@ActiveProfiles("test")
@SpringBootTest
abstract class AbstractTest {
    @Autowired
    protected lateinit var messageSource: MessageSource
}

/**
 * Test Super Class Example for Test Classes with datasource
 *
 * @author Aivyss
 * @since 12/01/2022
 */
@Transactional
abstract class PersistAbstractTest: AbstractTest()