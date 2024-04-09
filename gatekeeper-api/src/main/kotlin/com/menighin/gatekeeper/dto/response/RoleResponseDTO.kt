package com.menighin.gatekeeper.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.menighin.gatekeeper.model.Role

class RoleResponseDTO (

    @JsonProperty("id")
    var id: Long,

    @JsonProperty("name")
    var name: String

) {
    constructor(role: Role): this(role.id, role.name)
}