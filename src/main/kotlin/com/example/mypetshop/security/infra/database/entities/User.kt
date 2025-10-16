package com.example.mypetshop.security.infra.database.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Index
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.util.UUID

typealias UserId = UUID

@Entity
@Table(
    name = "users",
    schema = "public",
    indexes = [
        Index(name = "idx_user_email", columnList = "email"),
        Index(name = "idx_user_username", columnList = "username")
])
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    val id: UserId? = null,
    @Column(name = "email", nullable = false, unique = true)
    val email: String,
    @Column(name = "username", nullable = false, unique = true)
    val username: String,
    @Column(name = "password", nullable = false)
    val password: String,
    @Column(name = "email_verified", nullable = false)
    val emailVerified: Boolean = false,
    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    val createdAt: Instant = Instant.now()
)