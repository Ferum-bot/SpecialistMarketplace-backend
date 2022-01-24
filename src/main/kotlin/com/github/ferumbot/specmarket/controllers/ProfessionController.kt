package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.configs.SwaggerConfig
import com.github.ferumbot.specmarket.core.annotations.SwaggerVisible
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.UpdateProfessionDto
import com.github.ferumbot.specmarket.models.entities.Profession
import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.ProfessionService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@SwaggerVisible
@RequestMapping("api/professions")
@Tag(name = "Profession", description = SwaggerConfig.PROFESSION_CONTROLLER_DESCRIPTION)
class ProfessionController {

    @Autowired
    private lateinit var service: ProfessionService

    @GetMapping("/all")
    @Operation(summary = "Get all current available professions")
    fun getAllAvailableProfessions(): ResponseEntity<ApiResponse<Collection<Profession>>> {
        val professions = service.getAllAvailableProfessions()

        val response = ApiResponse.success(professions)
        return ResponseEntity.ok(response)
    }


    @GetMapping("/search/by_friendly_name")
    @Operation(summary = "Get all professions with friendly name")
    fun searchProfessionsByFriendlyName(
        @RequestParam(value = "friendly_name", required = true)
        friendlyName: String,
    ): ResponseEntity<ApiResponse<Collection<Profession>>> {
        val professions = service.searchProfessionsByFriendlyName(friendlyName)

        val response = ApiResponse.success(professions)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get/by_alias")
    @Operation(summary = "Get profession with alias")
    fun getProfessionByAlias(
        @RequestParam(value = "alias", required = true)
        alias: String
    ): ResponseEntity<ApiResponse<*>> {
        val profession = service.getProfessionByAlias(alias)

        return if (profession == null) {
            val response = ApiResponse.notFound { "Profession doesn't exists!" }
            ResponseEntity.ok(response)
        } else {
            val response = ApiResponse.success(profession)
            ResponseEntity.ok(response)
        }
    }

    @GetMapping("/get/by_id")
    @Operation(summary = "Get profession with id")
    fun getProfessionById(
        @RequestParam(value = "id", required = true)
        id: Long
    ): ResponseEntity<ApiResponse<*>> {
        val profession = service.getProfessionById(id)

        return if (profession == null) {
            val response = ApiResponse.notFound { "Profession doesn't exists" }
            ResponseEntity.ok(response)
        } else {
            val response = ApiResponse.success(profession)
            ResponseEntity.ok(response)
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Create new profession with parameters")
    fun createNewProfession(
        @Valid
        @RequestBody
        profession: ProfessionDto
    ): ResponseEntity<ApiResponse<Profession>> {
        val result = service.createNewProfession(profession)

        val response = ApiResponse.success(result)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/update/by_id")
    @Operation(summary = "Update current profession by it id")
    fun updateProfessionById(
        @RequestBody
        professionToUpdate: UpdateProfessionDto,

        @RequestParam(value = "id", required = true)
        id: Long
    ): ResponseEntity<ApiResponse<*>> {
        val result = service.updateProfessionById(id, professionToUpdate)

        return if (result == null) {
            val response = ApiResponse.notAcceptable { "Update failed!" }
            ResponseEntity.ok(response)
        } else {
            val response = ApiResponse.success(result)
            ResponseEntity.ok(response)
        }
    }

    @PutMapping("/update/by_alias")
    @Operation(summary = "Update current profession by it alias")
    fun updateProfessionByAlias(
        @RequestBody
        professionToUpdate: UpdateProfessionDto,

        @RequestParam(value = "alias", required = true)
        alias: String,
    ): ResponseEntity<ApiResponse<*>> {
        val result = service.updateProfessionByAlias(alias, professionToUpdate)

        return if (result == null) {
            val response = ApiResponse.notAcceptable { "Update failed!" }
            ResponseEntity.ok(response)
        } else {
            val response = ApiResponse.success(result)
            ResponseEntity.ok(response)
        }
    }

    @DeleteMapping("/delete/by_alias")
    @Operation(summary = "Delete profession by it alias")
    fun deleteProfessionByAlias(
        @RequestParam(value = "alias", required = true)
        alias: String,
    ): ResponseEntity<ApiResponse<*>> {
        service.deleteProfessionByAlias(alias)

        val response = ApiResponse.done { "Profession deleted!" }
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete/by_id")
    @Operation(summary = "Delete profession by it id")
    fun deleteProfessionById(
        @RequestParam(value = "id", required = true)
        id: Long
    ): ResponseEntity<ApiResponse<*>> {
        service.deleteProfessionById(id)

        val response = ApiResponse.done { "Profession deleted!" }
        return ResponseEntity.ok(response)
    }
}