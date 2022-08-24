package com.example.hellocity.repositories

import com.example.hellocity.entitites.City
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param

interface CityRepository : CrudRepository<City, Long> {

    @Query("SELECT * FROM cities WHERE slug = :slug", nativeQuery = true)
    fun findBySlug(@Param("slug") slug: String): City?

    @Query("SELECT * FROM cities ORDER BY added_at DESC", nativeQuery = true)
    fun findAllByOrderByAddedAtDesc(): Iterable<City>
}
