package com.menighin.gatekeeper.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.menighin.gatekeeper.model.Permission
import com.menighin.gatekeeper.model.Role

class PermissionResponseDTO (

    @JsonProperty("id")
    var id: Long,

    @JsonProperty("name")
    var name: String

) {
    constructor(permission: Permission): this(permission.id, permission.name)
}