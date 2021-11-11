package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.ProfessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController("api/professions")
@Validated
class ProfessionController {

    @Autowired
    private lateinit var service: ProfessionService

    @GetMapping("/all")
    fun getAllAvailableProfessions(): ResponseEntity<ApiResponse<*>> {
        val professions = service.getAllAvailableProfessions()

        val response = ApiResponse.success(professions)
        return ResponseEntity.ok(response)
    }


    @GetMapping("/search/by_friendly_name")
    fun searchProfessionsByFriendlyName(
        @RequestParam(value = "friendly_name", required = true)
        friendlyName: String,
    ): ResponseEntity<ApiResponse<*>> {
        val professions = service.searchProfessionsByFriendlyName(friendlyName)

        val response = ApiResponse.success(professions)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/get/by_alias")
    fun getProfessionByAlias(): ResponseEntity<ApiResponse<*>> {
        TODO()
    }

    @GetMapping("/get/by_id")
    fun getProfessionById(): ResponseEntity<ApiResponse<*>> {
        TODO()
    }

    @PostMapping("/create")
    fun createNewProfession(): ResponseEntity<ApiResponse<*>> {
        TODO()
    }

    @PutMapping("/update/by_id")
    fun updateProfessionById(): ResponseEntity<ApiResponse<*>> {
        TODO()
    }

    @PutMapping("/update/by_alias")
    fun updateProfessionByAlias(): ResponseEntity<ApiResponse<*>> {
        TODO()
    }

    @DeleteMapping("/delete/by_alias")
    fun deleteProfessionByAlias(): ResponseEntity<ApiResponse<*>> {
        TODO()
    }

    @DeleteMapping("/delete/by_id")
    fun deleteProfessionById(): ResponseEntity<ApiResponse<*>> {
        TODO()
    }
}