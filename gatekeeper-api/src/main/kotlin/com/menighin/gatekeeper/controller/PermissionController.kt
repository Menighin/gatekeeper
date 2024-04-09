package com.menighin.gatekeeper.controller

import com.menighin.gatekeeper.dto.request.PermissionRequestDTO
import com.menighin.gatekeeper.dto.response.ResponseCollectionDTO
import com.menighin.gatekeeper.dto.response.ResponseDTO
import com.menighin.gatekeeper.dto.response.PermissionResponseDTO
import com.menighin.gatekeeper.service.PermissionService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Permission

@RestController
@RequestMapping("/permissions")
class PermissionController(
    private val permissionService: PermissionService
) {

    @GetMapping
    fun getPermissions(pageable: Pageable) = ResponseEntity.ok(ResponseCollectionDTO.fromPage(permissionService.getPermissions(pageable).map { PermissionResponseDTO(it) }))

    @GetMapping("/{id}")
    fun getPermissionById(@PathVariable("id") id: Long) = ResponseEntity.ok().body(ResponseDTO.from(permissionService.getPermissionById(id)?.apply(::PermissionResponseDTO)))

    @PostMapping
    fun createPermission(@RequestBody permissionDTO: PermissionRequestDTO): ResponseEntity<ResponseDTO<PermissionResponseDTO>> {
        val permission = permissionService.savePermission(permissionDTO)
        return ResponseEntity.ok().body(ResponseDTO.from(PermissionResponseDTO(permission)))
    }

    @PutMapping("/{id}")
    fun updatePermission(@PathVariable("id") id: Long, @RequestBody permissionDTO: PermissionRequestDTO): ResponseEntity<ResponseDTO<PermissionResponseDTO>> {
        val permission = permissionService.updatePermission(permissionDTO.withId(id))
        return ResponseEntity.ok(ResponseDTO.from(PermissionResponseDTO(permission)))
    }

    @DeleteMapping("/{id}")
    fun deletePermission(@PathVariable("id") id: Long) = ResponseEntity.ok(permissionService.deletePermission(id))

}