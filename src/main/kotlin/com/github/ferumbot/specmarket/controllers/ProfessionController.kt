package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.ProfessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController("api/professions")
class ProfessionController {

    @Autowired
    private lateinit var service: ProfessionService

    @GetMapping("/all")
    fun getAllAvailableProfessions(): ResponseEntity<ApiResponse<*>> {

    }


    @GetMapping("/search/by_friendly_name")
    fun searchProfessionsByFriendlyName(): ResponseEntity<ApiResponse<*>> {

    }

    @GetMapping("/get/by_alias")
    fun getProfessionByAlias(): ResponseEntity<ApiResponse<*>> {

    }

    @GetMapping("/get/by_id")
    fun getProfessionById(): ResponseEntity<ApiResponse<*>> {

    }

    @PostMapping("/create")
    fun createNewProfession(): ResponseEntity<ApiResponse<*>> {

    }

    @PutMapping("/update/by_id")
    fun updateProfessionById(): ResponseEntity<ApiResponse<*>> {

    }

    @PutMapping("/update/by_alias")
    fun updateProfessionByAlias(): ResponseEntity<ApiResponse<*>> {

    }

    @DeleteMapping("/delete/by_alias")
    fun deleteProfessionByAlias(): ResponseEntity<ApiResponse<*>> {

    }

    @DeleteMapping("/delete/by_id")
    fun deleteProfessionById(): ResponseEntity<ApiResponse<*>> {

    }
}