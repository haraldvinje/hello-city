package com.example.hellocity.controllers

import com.example.hellocity.entitites.City
import com.example.hellocity.entitites.NewCity
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

@RestController
@RequestMapping("/api/city")
class CityController(private val cityService: CityService) {

    @GetMapping
    fun getAll(): Iterable<City> = cityService.getAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): City? =
        cityService.getById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "City with id $id does not exist")

    @GetMapping("/{slug}")
    fun getBySlug(@PathVariable slug: String): City? =
        cityService.getBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "City with slug $slug does not exist")

    @RequestMapping(method = [RequestMethod.POST])
    fun addCity(@RequestBody newCity: NewCity) {
        cityService.addCity(newCity)
    }

    @DeleteMapping("/{id}")
    fun deleteCity(@PathVariable id: Long) {
        try {
            cityService.deleteById(id)
        } catch (e: CityNotFoundException) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "City with id $id does not exist")
        }
    }
}
