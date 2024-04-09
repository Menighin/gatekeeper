package com.menighin.gatekeeper.config.error

import com.menighin.gatekeeper.dto.response.ResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest


@ControllerAdvice
class ErrorHandlingConfig {

    @ExceptionHandler(value = [Exception::class])
    protected fun handleInternalServerError(ex: Exception, request: WebRequest?): ResponseEntity<ResponseDTO<*>> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ResponseDTO.from(ex))
    }

}