package com.example.springProjectSnippets

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.MessageSource
import org.springframework.test.context.ActiveProfiles
import org.springframework.transaction.annotation.Transactional

@ActiveProfiles("test")
@SpringBootTest
abstract class AbstractTest {
    @Autowired
    protected lateinit var messageSource: MessageSource
}

@Transactional
abstract class PersistAbstractTest: AbstractTest()