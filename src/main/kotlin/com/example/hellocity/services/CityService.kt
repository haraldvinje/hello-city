package com.example.hellocity.services

import com.example.hellocity.exceptions.CityNotFoundException
import com.example.hellocity.extensions.unwrap
import com.example.hellocity.models.City
import com.example.hellocity.models.CityDto
import com.example.hellocity.repositories.CityRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class CityService(private val cityRepository: CityRepository) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    fun getAll(): List<City> = cityRepository.findAllByOrderByAddedAt().toList()

    fun getById(id: Long) =
        cityRepository.findById(id).unwrap() ?: throw CityNotFoundException("City with ID $id not found")

    fun getBySlug(slug: String): City? =
        cityRepository.findBySlug(slug).unwrap() ?: run {
            logger.info("City with slug $slug not found")
            throw CityNotFoundException("City with slug $slug not found")
        }

    fun getAllByName(name: String): List<City> = cityRepository.findAllByName(name).toList()

    fun addCity(cityDTO: CityDto) {
        val city = City(cityDTO.name, cityDTO.description)
        val allMatchingSlugs = cityRepository.findAllBySlugContainingOrderBySlugDesc(city.slug).map { it.slug }.toList()
        if (allMatchingSlugs.isNotEmpty()) {
            val newSlug = generateNewSlug(city.slug, allMatchingSlugs)
            city.slug = newSlug
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

    private fun generateNewSlug(attemptedSlug: String, usedSlugs: List<String>): String {
        val latestSlug = usedSlugs.filter { it.matches(Regex("""$attemptedSlug(-\d+)*$""")) }.maxOrNull()
            ?: return attemptedSlug
        val number = if (latestSlug.contains("-")) latestSlug.split("-").last().toInt() + 1 else 2
        return "$attemptedSlug-$number"
    }
}
