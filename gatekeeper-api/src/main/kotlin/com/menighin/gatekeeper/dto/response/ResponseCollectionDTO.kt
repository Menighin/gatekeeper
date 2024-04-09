package com.menighin.gatekeeper.dto.response

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.domain.Page

data class ResponseCollectionDTO<T>(

    override val data: Collection<T>?,

    override val errors: Collection<ErrorDTO>?,

    @JsonProperty("size")
    val size: Int? = null,

    @JsonProperty("total")
    val total: Int? = null,

    @JsonProperty("page")
    val page: Int? = null
): ResponseDTO<Collection<T>>(data, errors) {

    companion object {
        fun <T> fromPage(page: Page<T>): ResponseCollectionDTO<T>
            = ResponseCollectionDTO(page.content, null, page.size, page.totalPages, page.number)
    }

}