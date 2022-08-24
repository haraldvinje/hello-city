package com.example.hellocity.controllers

import com.example.hellocity.entitites.City
import com.example.hellocity.entitites.NewCity
import com.example.hellocity.repositories.CityRepository
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
class CityController(private val repository: CityRepository) {

    @GetMapping
    fun findAll(): MutableIterable<City> = repository.findAll()

    @GetMapping("/{slug}")
    fun findOne(@PathVariable slug: String) =
        repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This city does not exist")

    @RequestMapping(method = [RequestMethod.POST])
    fun addCity(@RequestBody newCity: NewCity) {
        repository.save(City(newCity.name, newCity.description))
    }

    @DeleteMapping("/{id}")
    fun deleteCity(@PathVariable id: Long) {
        repository.deleteById(id)
    }
}
