package com.menighin.gatekeeper.service

import com.menighin.gatekeeper.dto.request.PermissionRequestDTO
import com.menighin.gatekeeper.model.Permission
import com.menighin.gatekeeper.repository.PermissionRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PermissionService(
    private val permissionRepository: PermissionRepository
) {

    fun getPermissions(pageable: Pageable): Page<Permission> {
        val data = permissionRepository.findAll(pageable)
        return data
    }

    fun getPermissionById(id: Long) = permissionRepository.findByIdOrNull(id)

    fun savePermission(permission: PermissionRequestDTO) = permissionRepository.save(permission.toEntity())

    fun deletePermission(id: Long) = permissionRepository.deleteById(id)

    fun updatePermission(permission: PermissionRequestDTO) = permissionRepository.save(permission.toEntity())

}