package com.github.ferumbot.specmarket.controllers.advice

import com.github.ferumbot.specmarket.exceptions.ProfessionNotExists
import com.github.ferumbot.specmarket.exceptions.ProfessionWasNotDeleted
import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.models.response.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(ProfessionWasNotDeleted::class)
    fun professionWasNotDeleted(ex: ProfessionWasNotDeleted): ResponseEntity<ApiResponse<ErrorResponse>> {
        val errorResponse = ErrorResponse(
            cause = ex.reason,
            totalExceptions = ex.suppressed.map { it.toString() }
        )
        val response = ApiResponse(
            statusCode = HttpStatus.CONFLICT.value(),
            statusMessage = "Conflict",
            additionalMessage = "Error arose",
            errorMessage = ex.localizedMessage,
            data = errorResponse
        )

        return ResponseEntity.ok(response)
    }

    @ExceptionHandler(ProfessionNotExists::class)
    fun professionNotExists(ex: ProfessionNotExists): ResponseEntity<ApiResponse<ErrorResponse>> {
        val errorResponse = ErrorResponse(
            cause = ex.reason,
            totalExceptions = ex.suppressed.map { it.toString() }
        )
        val response = ApiResponse(
            statusCode = HttpStatus.NOT_FOUND.value(),
            statusMessage = "Not found",
            additionalMessage = "Not exists",
            errorMessage = ex.localizedMessage,
            data = errorResponse,
        )

        return ResponseEntity.ok(response)
    }

    @ExceptionHandler(Exception::class)
    fun onExceptionRaised(ex: Exception): ResponseEntity<ApiResponse<ErrorResponse>> {
        val errorResponse = ErrorResponse(
            cause = ex.cause?.toString(),
            totalExceptions = ex.suppressed?.map { it.toString() }
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