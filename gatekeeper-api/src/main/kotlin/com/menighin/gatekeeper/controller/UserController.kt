package com.menighin.gatekeeper.controller

import com.menighin.gatekeeper.dto.request.PermissionRequestDTO
import com.menighin.gatekeeper.dto.request.RoleRequestDTO
import com.menighin.gatekeeper.dto.request.UserRequestDTO
import com.menighin.gatekeeper.dto.response.JwtResponseDTO
import com.menighin.gatekeeper.dto.response.ResponseDTO
import com.menighin.gatekeeper.dto.response.UserResponseDTO
import com.menighin.gatekeeper.service.JwtService
import com.menighin.gatekeeper.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/signup")
    fun signup(@RequestBody dto: UserRequestDTO): ResponseEntity<ResponseDTO<UserResponseDTO>> {
        val data = userService.signup(dto)
        return ResponseEntity.ok(ResponseDTO.from(data))
    }

    @PostMapping("/login")
    fun login(@RequestBody dto: UserRequestDTO): ResponseEntity<ResponseDTO<JwtResponseDTO>> {
        val token = userService.authenticate(dto)
        return ResponseEntity.ok(ResponseDTO.from(token))
    }

    @PutMapping("/{id}/add-role")
    fun addRole(@PathVariable("id") userId: Long, @RequestBody dto: RoleRequestDTO): ResponseEntity<ResponseDTO<UserResponseDTO>> {
        val user = userService.addRole(userId, dto)
        return ResponseEntity.ok(ResponseDTO.from(UserResponseDTO(user)))
    }

    @PutMapping("/{id}/add-permission")
    fun addPermission(@PathVariable("id") userId: Long, @RequestBody dto: PermissionRequestDTO): ResponseEntity<ResponseDTO<UserResponseDTO>> {
        val user = userService.addPermission(userId, dto)
        return ResponseEntity.ok(ResponseDTO.from(UserResponseDTO(user)))
    }
}