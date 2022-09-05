package com.example.hellocity.controllers

import com.example.hellocity.models.City
import com.example.hellocity.models.NewCity
import com.example.hellocity.services.CityService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import javax.validation.Valid

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

    @RequestMapping(method = [RequestMethod.POST])
    fun addCity(@Valid @RequestBody newCity: NewCity) {
        cityService.addCity(newCity)
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
