package com.menighin.gatekeeper.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.menighin.gatekeeper.model.User

class UserResponseDTO (

    @JsonProperty("id")
    var id: Long,

    @JsonProperty("username")
    var username: String,

    @JsonProperty("enabled")
    var enabled: Boolean,

    @JsonProperty("locked")
    var locked: Boolean,

    @JsonProperty("roles")
    var roles: List<RoleResponseDTO> = emptyList(),

    @JsonProperty("permissions]")
    var permissions: List<PermissionResponseDTO> = emptyList()

) {
    constructor(user: User): this(
        user.id,
        user.username,
        user.isEnabled,
        user.locked,
        user.userRoles.map { RoleResponseDTO(it.role) },
        user.userPermissions.map { PermissionResponseDTO(it.permission) }
    )
}