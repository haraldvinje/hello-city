package com.example.hellocity.repositories

import com.example.hellocity.models.City
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import java.util.Optional

interface CityRepository : CrudRepository<City, Long> {

    fun findBySlug(@Param("slug") slug: String): Optional<City>

    fun findAllBySlugContainingOrderBySlugDesc(@Param("name") name: String): Iterable<City>

    fun findAllByOrderByAddedAtDesc(): Iterable<City>

    fun findAllByName(@Param("name") name: String): Iterable<City>
}
