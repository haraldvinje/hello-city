package com.example.hellocity

import com.example.hellocity.controllers.CityRestController
import com.example.hellocity.models.NewCity
import com.example.hellocity.repositories.CityRepository
import com.example.hellocity.services.CityService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class HelloCityApplicationTests : ContainerBaseTest() {

    @Autowired
    private lateinit var cityRestController: CityRestController

    @Autowired
    private lateinit var cityService: CityService

    @Autowired
    private lateinit var cityRepository: CityRepository

    @BeforeEach
    fun deleteRecords() {
        cityRepository.deleteAll()
    }

    @Test
    fun `should be able to add two cities and retrieve them afterwards`() {
        // When
        val oslo = "Oslo"
        cityRestController.addCity(NewCity(oslo, "Capital of Norway"))
        cityRestController.addCity(NewCity("Bergen", "Rainy city"))

        // Then
        val allCities = cityRestController.getAll()
        val expectedNumberOfCities = 2
        val actualNumberOfCities = allCities.size
        assertEquals(expectedNumberOfCities, actualNumberOfCities)
        assertEquals(allCities.first { it.name == oslo }.name, oslo)
    }

    @Test
    fun `should create different slugs when multiple cities of same name are added`() {
        // When
        val cityName = "Manchester"
        cityRestController.addCity(NewCity(cityName, "City in London"))
        cityRestController.addCity(NewCity(cityName, "City in New Hampshire"))
        cityRestController.addCity(NewCity(cityName, "Some other Manchester"))

        // Then
        val cities = cityService.getAllByName(cityName)
        val uniqueSlugs = cities.map { it.slug }.toSet()
        val (city, city2) = cities
        assertNotEquals(city.slug, city2.slug)
        assertEquals(cities.size, uniqueSlugs.size)
    }
}
