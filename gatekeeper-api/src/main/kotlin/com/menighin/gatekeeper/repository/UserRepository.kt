package com.menighin.gatekeeper.repository

import com.menighin.gatekeeper.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface UserRepository : JpaRepository<User, Long> {

    @Query("select u " +
            "from User u " +
            "left join fetch u.userRoles ur left join fetch ur.role " +
            "left join fetch u.userPermissions up left join fetch up.permission " +
            "where u.username = :username"
    )
    fun findByUsername(username: String?): User
}