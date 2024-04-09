package com.menighin.gatekeeper.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ErrorDTO(

    @JsonProperty("code")
    val code: String? = null,

    @JsonProperty("message")
    val message: String? = null

)