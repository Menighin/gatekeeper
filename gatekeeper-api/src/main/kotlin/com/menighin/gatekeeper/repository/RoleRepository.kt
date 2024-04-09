package com.menighin.gatekeeper.repository

import com.menighin.gatekeeper.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(name: String): Role?

}