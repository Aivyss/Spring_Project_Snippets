package com.example.springProjectSnippets.api.domain

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
@Table(name = "MST_USER")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_KEY")
    val id: Long,

    @Column(name = "EMAIL", nullable = false, unique = true)
    val email: String,

    @Column(name = "PASSWORD", nullable = false)
    val password: String,

    @Column(name = "USERNAME", nullable = false)
    val username: String,
)

object UserFactory {
    fun create(email: String, password: String, username: String): User = User(
        id = 0L,
        email = email,
        password = password,
        username = username,
    )
}

interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmail(email: String): Boolean
}