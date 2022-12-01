package com.example.springProjectSnippets.endpoint

import com.example.springProjectSnippets.api.role.Role
import com.example.springProjectSnippets.api.role.RoleAdmin
import com.example.springProjectSnippets.api.role.RoleManager
import com.example.springProjectSnippets.domain.UserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

/**
 * Admin Role initializer when application is loaded
 * if you run multiple application(containers, EC2s), then you need to apply Job Processing (example: Database Lock)
 *
 * @author Aivyss
 * @since 12/02/2022
 */
@Service
class UserRoleInitializer(
    private val userRepository: UserRepository,
) {
    private var isApplicationLoaded: Boolean = false
    private val log: Logger = LoggerFactory.getLogger(UserRoleInitializer::class.java)

    @PostConstruct
    fun initialize() {
        if (!this.isApplicationLoaded) {
            doInitialize()
            this.isApplicationLoaded = true
        }
    }

    fun doInitialize() {
        adminInitialize()
        managerInitialize()
    }

    private fun adminInitialize() {
        try {
            for (admin in RoleAdmin.values()) {
                val user = userRepository.findByEmailAndDeletedFalse(admin.email)

                if (user != null && !user.roles.any { it.role == Role.ADMIN }) {
                    user.addRole(Role.ADMIN)
                    userRepository.save(user)
                }
            }
        } catch (e: Exception) {
            log.info(e.stackTraceToString())
        }
    }

    private fun managerInitialize() {
        try {
            for (manager in RoleManager.values()) {
                val user = userRepository.findByEmailAndDeletedFalse(manager.email)

                if (user != null && !user.roles.any { it.role == Role.MANAGER }) {
                    user.addRole(Role.MANAGER)
                    userRepository.save(user)
                }
            }
        } catch (e: Exception) {
            log.info(e.stackTraceToString())
        }
    }
}