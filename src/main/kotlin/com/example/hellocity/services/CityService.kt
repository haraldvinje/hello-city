package com.example.hellocity.services

import com.example.hellocity.exceptions.CityNotFoundException
import com.example.hellocity.extensions.unrwap
import com.example.hellocity.models.City
import com.example.hellocity.models.NewCity
import com.example.hellocity.repositories.CityRepository
import org.springframework.stereotype.Service

@Service
class CityService(private val cityRepository: CityRepository) {

    fun getAll(): Iterable<City> = cityRepository.findAllByOrderByAddedAtDesc()

    fun getById(id: Long): City? {
        return try {
            cityRepository.findById(id).unrwap()
        } catch (e: Exception) {
            throw CityNotFoundException("City with ID $id not found")
        }
    }

    fun getBySlug(slug: String): City? {
        return try {
            cityRepository.findBySlug(slug)
        } catch (e: Exception) {
            throw CityNotFoundException("City with slug $slug not found")
        }
    }

    fun addCity(newCity: NewCity) {
        cityRepository.save(
            City(newCity.name, newCity.description)
        )
    }

    fun deleteById(id: Long) {
        try {
            cityRepository.deleteById(id)
        } catch (e: Exception) {
            throw CityNotFoundException("City with ID $id not found")
        }
    }
}
