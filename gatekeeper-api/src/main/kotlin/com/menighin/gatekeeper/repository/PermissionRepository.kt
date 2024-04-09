package com.menighin.gatekeeper.repository

import com.menighin.gatekeeper.model.Permission
import org.springframework.data.jpa.repository.JpaRepository

interface PermissionRepository : JpaRepository<Permission, Long> {

    fun findByName(name: String): Permission?

}