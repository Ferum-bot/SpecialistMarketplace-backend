package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.configs.SwaggerConfig
import com.github.ferumbot.specmarket.exceptions.UndefinedProfileStatus
import com.github.ferumbot.specmarket.models.dto.SpecialistStatusDto
import com.github.ferumbot.specmarket.models.request.CreateSpecialistStatusRequest
import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.SpecialistStatusService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/specialistStatus")
@Tag(name = "Specialist Status", description = SwaggerConfig.SPECIALIST_STATUS_CONTROLLER_DESCRIPTION)
class SpecialistStatusController {

    @Autowired
    private lateinit var service: SpecialistStatusService

    @GetMapping("/all")
    @Operation(summary = "Получить все доступные статусы")
    fun getAllAvailableStatuses(): ApiResponse<Collection<SpecialistStatusDto>> {
        val result = service.getAllStatuses()
        return ApiResponse.success(result)
    }

    @GetMapping("/get")
    @Operation(summary = "Получить статус по его id")
    fun getStatusById(
        @RequestParam(value = "id", required = true)
        id: Long
    ): ApiResponse<SpecialistStatusDto> {
        val result = service.getStatusById(id)
            ?: throw UndefinedProfileStatus("Can't find status with id: $id")

        return ApiResponse.success(result)
    }

    @PatchMapping("/create")
    @Operation(summary = "Создать новый статус")
    fun createNewStatus(
        @RequestBody
        status: CreateSpecialistStatusRequest,
    ): ApiResponse<SpecialistStatusDto> {
        val result = service.createNewStatus(status)
        return ApiResponse.success(result)
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Удалить существующий статус")
    fun deleteStatus(
        @RequestParam(value = "id", required = true)
        id: Long,
    ): ApiResponse<*> {
        service.deleteStatus(id)
        return ApiResponse.done { "Profile status with id: $id was deleted." }
    }
}