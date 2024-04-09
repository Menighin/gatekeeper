package com.menighin.gatekeeper.dto.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.menighin.gatekeeper.model.Role

class UserRequestDTO (

    @JsonProperty("id")
    var id: Long?,

    @JsonProperty("username")
    var username: String,

    @JsonProperty("password")
    var password: String

) {
    fun withId(id: Long) = this.apply { this.id = id }
}