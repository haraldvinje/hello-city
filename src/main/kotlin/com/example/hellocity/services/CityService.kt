package com.example.hellocity.services

import com.example.hellocity.exceptions.CityNotFoundException
import com.example.hellocity.extensions.unwrap
import com.example.hellocity.models.City
import com.example.hellocity.models.NewCity
import com.example.hellocity.repositories.CityRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CityService(private val cityRepository: CityRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getAll(): List<City> = cityRepository.findAllByOrderByAddedAtDesc().toList()

    fun getById(id: Long) =
        cityRepository.findById(id).unwrap() ?: throw CityNotFoundException("City with ID $id not found")

    fun getBySlug(slug: String): City? =
        cityRepository.findBySlug(slug).unwrap() ?: run {
            logger.error("City with slug $slug not found")
            throw CityNotFoundException("City with slug $slug not found")
        }

    fun getAllByName(name: String): List<City> = cityRepository.findAllByName(name).toList()

    fun addCity(newCity: NewCity) {
        val city = City(newCity.name, newCity.description)
        val latestMatchingSlug = cityRepository.findAllBySlugContainingOrderBySlugDesc(city.slug).firstOrNull {
            it.slug.matches(Regex(it.slug.plus("|-[0-9]+")))
        }?.slug
        if (latestMatchingSlug?.contains("-") == true) {
            city.slug += "-" + latestMatchingSlug.split("-")[1].toInt().plus(1).toString()
        } else if (latestMatchingSlug?.isNotEmpty() == true) {
            city.slug += "-2"
        }
        cityRepository.save(city)
    }

    fun deleteById(id: Long) {
        try {
            cityRepository.deleteById(id)
        } catch (e: Exception) {
            throw CityNotFoundException("City with ID $id not found")
        }
    }
}
