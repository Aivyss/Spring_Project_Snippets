package com.example.springProjectSnippets.domain

import com.example.springProjectSnippets.api.role.Role
import org.springframework.data.jpa.repository.EntityGraph
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
    var username: String,
) {
    @OneToMany(mappedBy = "user", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    val roles: MutableList<UserRole> = mutableListOf()

    @Column(name = "DELETED", nullable = false)
    var deleted: Boolean = false

    fun addRole(role: Role): User {
        val userRole = UserRoleFactory.create(role = role, user = this)
        roles += userRole

        return this
    }
}

object UserFactory {
    fun create(email: String, password: String, username: String): User = User(
        id = 0L,
        email = email,
        password = password,
        username = username,
    )
}

interface UserRepository : JpaRepository<User, Long> {
    fun existsByEmailAndDeletedFalse(email: String): Boolean
    @EntityGraph(attributePaths = ["roles"])
    fun findByEmailAndDeletedFalse(email: String): User?
}