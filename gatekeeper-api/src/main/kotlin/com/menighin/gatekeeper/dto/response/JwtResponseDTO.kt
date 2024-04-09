package com.menighin.gatekeeper.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class JwtResponseDTO(

    @JsonProperty("token")
    val token: String

)