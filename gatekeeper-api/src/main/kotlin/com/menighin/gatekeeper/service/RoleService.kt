package com.menighin.gatekeeper.service

import com.menighin.gatekeeper.dto.request.RoleRequestDTO
import com.menighin.gatekeeper.dto.response.RoleResponseDTO
import com.menighin.gatekeeper.repository.RoleRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val roleRepository: RoleRepository
) {

    fun getRoles(pageable: Pageable): Page<RoleResponseDTO> {
        val data = roleRepository.findAll(pageable).map { RoleResponseDTO(it) }
        return data
    }

    fun getRoleById(id: Long) = roleRepository.findByIdOrNull(id)?.let { RoleResponseDTO(it) }

    fun saveRole(role: RoleRequestDTO) = RoleResponseDTO(roleRepository.save(role.toEntity()))

    fun deleteRole(id: Long) = roleRepository.deleteById(id)

    fun updateRole(role: RoleRequestDTO) = roleRepository.save(role.toEntity()).let { RoleResponseDTO(it) }

}