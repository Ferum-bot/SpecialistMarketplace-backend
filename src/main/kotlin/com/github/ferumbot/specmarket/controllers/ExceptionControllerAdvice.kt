package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.models.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler
    fun onExceptionRaised(ex: Exception): ResponseEntity<ApiResponse<*>> {
        val errorResponse =  ErrorResponse(
            cause = ex.cause?.toString(),
            totalExceptions = ex.suppressed.map { it.toString() }
        )
        val response = ApiResponse(
            statusCode = HttpStatus.BAD_REQUEST.value(),
            statusMessage = "Bad Request",
            additionalMessage = "Exception arose",
            errorMessage = ex.localizedMessage,
            data = errorResponse,
        )

        return ResponseEntity.ok(response)
    }
}