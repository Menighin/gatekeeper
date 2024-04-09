package com.menighin.gatekeeper.service

import com.menighin.gatekeeper.dto.request.PermissionRequestDTO
import com.menighin.gatekeeper.dto.request.RoleRequestDTO
import com.menighin.gatekeeper.dto.request.UserRequestDTO
import com.menighin.gatekeeper.dto.response.JwtResponseDTO
import com.menighin.gatekeeper.dto.response.UserResponseDTO
import com.menighin.gatekeeper.model.User
import com.menighin.gatekeeper.model.UserPermission
import com.menighin.gatekeeper.model.UserRole
import com.menighin.gatekeeper.repository.PermissionRepository
import com.menighin.gatekeeper.repository.RoleRepository
import com.menighin.gatekeeper.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val roleRepository: RoleRepository,
    private val permissionRepository: PermissionRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService
) {

    fun signup(user: UserRequestDTO) =
        UserResponseDTO(userRepository.save(User(0L, user.username, bCryptPasswordEncoder.encode(user.password))))

    fun findById(id: Long) = userRepository.findByIdOrNull(id)

    fun authenticate(userDTO: UserRequestDTO): JwtResponseDTO {
        val authenticationToken = UsernamePasswordAuthenticationToken(userDTO.username, userDTO.password)
        val authentication = authenticationManager.authenticate(authenticationToken)

        if (authentication.isAuthenticated) {
            val token = JwtResponseDTO(jwtService.generateToken(authentication.principal as User))
            return token
        }

        throw IllegalArgumentException("Username or password wrong")
    }

    fun addRole(userId: Long, roleRequestDTO: RoleRequestDTO): User {
        val user = userRepository.findByIdOrNull(userId)
        val role = roleRepository.findByName(roleRequestDTO.name)

        user?.userRoles?.add(UserRole(user.id, role?.id!!).apply {
            this.user = user
            this.role = role
        })

        return userRepository.save(user!!)
    }

    fun addPermission(userId: Long, permissionRequestDTO: PermissionRequestDTO): User {
        val user = userRepository.findByIdOrNull(userId)
        val permission = permissionRepository.findByName(permissionRequestDTO.name)

        user?.userPermissions?.add(UserPermission(user.id, permission?.id!!).apply {
            this.user = user
            this.permission = permission
        })

        return userRepository.save(user!!)
    }

}