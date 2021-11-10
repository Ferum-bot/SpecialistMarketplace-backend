package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.models.response.ApiResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler()
    fun onExceptionRaised(ex: Exception): ResponseEntity<ApiResponse<*>> {

    }
}