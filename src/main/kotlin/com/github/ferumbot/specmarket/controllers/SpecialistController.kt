package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.core.extensions.ifNull
import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.SpecialistService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Validated
@RestController
@RequestMapping("api/specialists")
class SpecialistController {

    @Autowired
    private lateinit var service: SpecialistService

    @GetMapping("/all")
    fun getAllSpecialists(
        @Min(1)
        @RequestParam(value = "page_number", required = true)
        pageNumber: Int,

        @Min(1)
        @Max(50)
        @RequestParam(value = "page_size", required = true)
        pageSize: Int,
    ): ApiResponse<*> {
        val specialists = service.getAllSpecialists(pageNumber, pageSize)
        return ApiResponse.success(specialists)
    }

    @GetMapping("/byId")
    fun getSpecialistById(
        @RequestParam(value = "id", required = true)
        id: Long,
    ): ApiResponse<*> {
        val specialist = service.getSpecialistById(id)

        specialist.ifNull {
            return ApiResponse.notFound { "Specialist not found" }
        }

        return ApiResponse.success(specialist)
    }

    @GetMapping("/all/byProfessionId")
    fun getAllSpecialistsByProfessionId(
        @RequestParam(value = "id", required = true)
        id: Long,

        @Min(1)
        @RequestParam(value = "page_number", required = true)
        pageNumber: Int,

        @Min(1)
        @Max(50)
        @RequestParam(value = "page_size", required = true)
        pageSize: Int,
    ): ApiResponse<*> {
        val specialists = service.getAllSpecialistsByProfessionId(id, pageNumber, pageSize)
        return ApiResponse.success(specialists)
    }

    @GetMapping("/all/byProfessionAlias")
    fun getAllSpecialistsByProfessionAlias(
        @RequestParam(value = "alias", required = true)
        alias: String,

        @Min(1)
        @RequestParam(value = "page_number", required = true)
        pageNumber: Int,

        @Min(1)
        @Max(50)
        @RequestParam(value = "page_size", required = true)
        pageSize: Int,
    ): ApiResponse<*> {
        val specialists = service.getAllSpecialistsByProfessionAlias(alias, pageNumber, pageSize)
        return ApiResponse.success(specialists)
    }
}