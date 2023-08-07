package com.example.hellocity.controllers

import com.example.hellocity.models.City
import com.example.hellocity.models.CityDto
import com.example.hellocity.services.CityService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/city")
class CityRestController(private val cityService: CityService) {

    @GetMapping
    fun getAll(): List<City> = cityService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): City? =
        cityService.getById(id)

    @GetMapping("/slug/{slug}")
    fun getBySlug(@PathVariable slug: String): City? =
        cityService.getBySlug(slug)

    @PostMapping()
    fun addCity(
        @Valid @RequestBody
        cityDTO: CityDto
    ) {
        cityService.addCity(cityDTO)
    }

    @DeleteMapping("/{id}")
    fun deleteCity(@PathVariable id: Long) {
        try {
            cityService.deleteById(id)
        } catch (e: IllegalArgumentException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "City with id $id does not exist")
        }
    }
}
