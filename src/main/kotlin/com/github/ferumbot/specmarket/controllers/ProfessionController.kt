package com.github.ferumbot.specmarket.controllers

import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.UpdateProfessionDto
import com.github.ferumbot.specmarket.models.response.ApiResponse
import com.github.ferumbot.specmarket.services.ProfessionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@Validated
@RestController
@RequestMapping("api/professions/")
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
    fun createNewProfession(
        @Valid
        @RequestBody
        profession: ProfessionDto
    ): ResponseEntity<ApiResponse<*>> {
        val result = service.createNewProfession(profession)

        val response = ApiResponse.success(result)
        return ResponseEntity.ok(response)
    }

    @PutMapping("/update/by_id")
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
    fun deleteProfessionByAlias(
        @RequestParam(value = "alias", required = true)
        alias: String,
    ): ResponseEntity<ApiResponse<*>> {
        service.deleteProfessionByAlias(alias)

        val response = ApiResponse.done { "Profession deleted!" }
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/delete/by_id")
    fun deleteProfessionById(
        @RequestParam(value = "id", required = true)
        id: Long
    ): ResponseEntity<ApiResponse<*>> {
        service.deleteProfessionById(id)

        val response = ApiResponse.done { "Profession deleted!" }
        return ResponseEntity.ok(response)
    }
}