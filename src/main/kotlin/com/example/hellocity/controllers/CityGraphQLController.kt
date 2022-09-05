package com.example.hellocity.controllers

import com.example.hellocity.models.City
import com.example.hellocity.services.CityService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class CityGraphQLController(private val cityService: CityService) {

    @QueryMapping
    fun cities(): List<City> = cityService.getAll()

    @QueryMapping
    fun cityById(@Argument id: Long): City? {
        return cityService.getById(id)
    }
}
