package com.example.mypetshop.security.infra.database.repositories

import com.example.mypetshop.security.infra.database.entities.User
import com.example.mypetshop.security.infra.database.entities.UserId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User, UserId> {
    fun findByUsernameAndEmail(username: String, email: String): User?
}