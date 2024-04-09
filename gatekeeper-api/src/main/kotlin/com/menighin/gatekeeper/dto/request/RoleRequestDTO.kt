package com.menighin.gatekeeper.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.menighin.gatekeeper.model.Role

class RoleRequestDTO (

    @JsonProperty("id")
    var id: Long?,

    @JsonProperty("name")
    var name: String

) {

    fun toEntity() = Role(id ?: 0L, name)

    fun withId(id: Long) = this.apply { this.id = id }

}