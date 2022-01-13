package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.configs.SwaggerConfig
import com.github.ferumbot.specmarket.core.annotations.SwaggerVisible
import com.github.ferumbot.specmarket.core.extensions.ifNull
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.SpecialistService
import io.swagger.annotations.Api
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
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
@SwaggerVisible
@RequestMapping("api/specialists")
@Api(description = SwaggerConfig.SPECIALIST_CONTROLLER_DESCRIPTION)
class SpecialistController {

    @Autowired
    private lateinit var service: SpecialistService

    @GetMapping("/all")
    @Operation(summary = "Get all service specialists")
    fun getAllSpecialists(
        @Min(value = 1, message = "Page number must be greater than 0")
        @RequestParam(value = "page_number", required = true)
        pageNumber: Int,

        @Min(value = 1, message = "Size must be greater than 0")
        @Max(value = 50, message = "Size must be not greater than 50")
        @RequestParam(value = "page_size", required = true)
        pageSize: Int,
    ): ApiResponse<Collection<SpecialistDto>> {
        val specialists = service.getAllSpecialists(pageNumber, pageSize)
        return ApiResponse.success(specialists)
    }

    @GetMapping("/byId")
    @Operation(summary = "Get specialist by it internal id")
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
    @Operation(summary = "Get all specialists with this profession by it id")
    fun getAllSpecialistsByProfessionId(
        @RequestParam(value = "id", required = true)
        id: Long,

        @Min(value = 1, message = "Page number must be greater than 0")
        @RequestParam(value = "page_number", required = true)
        pageNumber: Int,

        @Min(value = 1, message = "Size must be greater than 0")
        @Max(value = 50, message = "Size must be not greater than 50")
        @RequestParam(value = "page_size", required = true)
        pageSize: Int,
    ): ApiResponse<Collection<SpecialistDto>> {
        val specialists = service.getAllSpecialistsByProfessionId(id, pageNumber, pageSize)
        return ApiResponse.success(specialists)
    }

    @GetMapping("/all/byProfessionAlias")
    @Operation(summary = "Get all specialists with this profession by it alias")
    fun getAllSpecialistsByProfessionAlias(
        @RequestParam(value = "alias", required = true)
        alias: String,

        @Min(value = 1, message = "Page number must be greater than 0")
        @RequestParam(value = "page_number", required = true)
        pageNumber: Int,

        @Min(value = 1, message = "Size must be greater than 0")
        @Max(value = 50, message = "Size must be not greater than 50")
        @RequestParam(value = "page_size", required = true)
        pageSize: Int,
    ): ApiResponse<Collection<SpecialistDto>> {
        val specialists = service.getAllSpecialistsByProfessionAlias(alias, pageNumber, pageSize)
        return ApiResponse.success(specialists)
    }
}