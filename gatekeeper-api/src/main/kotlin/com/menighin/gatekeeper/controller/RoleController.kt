package com.menighin.gatekeeper.controller

import com.menighin.gatekeeper.dto.request.RoleRequestDTO
import com.menighin.gatekeeper.dto.response.ResponseCollectionDTO
import com.menighin.gatekeeper.dto.response.ResponseDTO
import com.menighin.gatekeeper.dto.response.RoleResponseDTO
import com.menighin.gatekeeper.service.RoleService
import jakarta.annotation.security.RolesAllowed
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/roles")
class RoleController(
    private val roleService: RoleService
) {

    @GetMapping
    @PreAuthorize("hasAuthority('READ_ROLE')")
    fun getRoles(pageable: Pageable) = ResponseEntity.ok(ResponseCollectionDTO.fromPage(roleService.getRoles(pageable)))

    @GetMapping("/{id}")
    fun getRoleById(@PathVariable("id") id: Long) = ResponseEntity.ok().body(ResponseDTO.from(roleService.getRoleById(id)))

    @PostMapping
    fun createRole(@RequestBody roleDTO: RoleRequestDTO): ResponseEntity<ResponseDTO<RoleResponseDTO>> {
        val role = roleService.saveRole(roleDTO)
        return ResponseEntity.ok().body(ResponseDTO.from(role))
    }

    @PutMapping("/{id}")
    fun updateRole(@PathVariable("id") id: Long, @RequestBody roleDTO: RoleRequestDTO): ResponseEntity<ResponseDTO<RoleResponseDTO>> {
        val role = roleService.updateRole(roleDTO.withId(id))
        return ResponseEntity.ok(ResponseDTO.from(role))
    }

    @DeleteMapping("/{id}")
    fun deleteRole(@PathVariable("id") id: Long) = ResponseEntity.ok(roleService.deleteRole(id))

}