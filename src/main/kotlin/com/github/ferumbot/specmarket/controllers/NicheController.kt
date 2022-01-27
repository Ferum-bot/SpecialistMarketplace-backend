package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.configs.SwaggerConfig
import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.entities.specifications.Niche
import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.NicheService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/niche")
@Tag(name = "Niche", description = SwaggerConfig.NICHE_CONTROLLER_DESCRIPTION)
class NicheController  {

    @Autowired
    private lateinit var service: NicheService

    @GetMapping("/all")
    @Operation(summary = "Get all current available niches")
    fun getAllAvailableNiches(): ResponseEntity<ApiResponse<Collection<Niche>>> {
        val result = service.getAllAvailableNiches()
        val response = ApiResponse.success(result)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/byId")
    @Operation(summary = "Get niche by it id")
    fun getNicheById(
        @RequestParam(value = "id", required = true)
        id: Long,
    ): ResponseEntity<ApiResponse<*>> {
        val result = service.getNicheById(id)
        val response = result?.run { ApiResponse.success(this) }
            ?: ApiResponse.notFound { "Niche with id: $id not found!" }

        return ResponseEntity.ok(response)
    }

    @GetMapping("/byAlias")
    @Operation(summary = "Get niche by it program alias")
    fun getNicheByAlias(
        @RequestParam(value = "alias", required = true)
        alias: String,
    ): ResponseEntity<ApiResponse<*>> {
        val result = service.getNicheByAlias(alias)
        val response = result?.run { ApiResponse.success(this) }
            ?: ApiResponse.notFound { "Niche with alias: $alias not found!" }

        return ResponseEntity.ok(response)
    }

    @PatchMapping("/create")
    @Operation(summary = "Create new niche")
    fun createNewNiche(
        @RequestBody
        newNiche: NicheDto
    ): ResponseEntity<ApiResponse<Niche>> {
        val result = service.createNewNiche(newNiche)
        val response = ApiResponse.success(result)
        return ResponseEntity.ok(response)
    }
}