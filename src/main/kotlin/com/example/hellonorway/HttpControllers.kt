package com.example.hellonorway

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import java.util.*

@RestController
class MessageResource {
    @GetMapping
    fun index(): List<Message> = listOf(
        Message("1", "Hello!"),
        Message("2", "Bonjour!"),
        Message("3", "Privet!"),
    )
}

@RestController
@RequestMapping("/api/city")
class CityController(private val repository: CityRepository) {

  @GetMapping
  fun findAll() = repository.findAll()

  @GetMapping("/{slug}")
  fun findOne(@PathVariable slug: String) =
      repository.findBySlug(slug) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This city does not exist")


  @RequestMapping(method = [RequestMethod.POST])
  fun addCity(@RequestBody newCity: NewCity) {
      repository.save(City(newCity.name, newCity.description))
  }
}

