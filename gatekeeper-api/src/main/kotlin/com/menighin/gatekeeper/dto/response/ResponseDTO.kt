package com.menighin.gatekeeper.dto.response

import com.fasterxml.jackson.annotation.JsonProperty

open class ResponseDTO<T>(

    @JsonProperty("data")
    open val data: T?,

    @JsonProperty("errors")
    open val errors: Collection<ErrorDTO>? = null
) {

    companion object {
        fun <T> from(data: T?) = ResponseDTO(data)

        fun from(ex: Exception): ResponseDTO<Unit> {
            val error = ErrorDTO(message = ex.message)
            return ResponseDTO(null, listOf(error))
        }
    }

}