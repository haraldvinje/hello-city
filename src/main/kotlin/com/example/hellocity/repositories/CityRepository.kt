package com.example.hellocity.repositories

import com.example.hellocity.entitites.City
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

interface CityRepository : CrudRepository<City, Long> {

    @Query("SELECT * FROM cities WHERE slug = :slug", nativeQuery = true)
    fun findBySlug(@Param("slug") slug: String): City?

    @Query("SELECT * FROM cities ORDER BY addedAt DESC", nativeQuery = true)
    fun findAllByOrderByAddedAtDesc(): Iterable<City>

    @Transactional
    @Modifying
    @Query(
        "INSERT INTO cities (id, name, description, slug, added_at) " +
            "VALUES (:id, :name, :description, :slug, :addedAt)",
        nativeQuery = true
    )
    fun addCity(
        @Param("id") id: Long?,
        @Param("name") name: String,
        @Param("description") description: String,
        @Param("slug") slug: String,
        @Param("addedAt") addedAt: LocalDateTime
    )
}
