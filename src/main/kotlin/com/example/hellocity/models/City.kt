package com.example.hellocity.models

import com.example.hellocity.extensions.toSlug
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.Pattern
import java.time.LocalDateTime

@Table(name = "cities")
@Entity
class City(
    var name: String,
    var description: String,
    var slug: String = name.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    var id: Long = 0
)

data class CityDto(
    @get:Pattern(regexp = "^[a-zA-Z\\s-]+")
    var name: String,
    var description: String
)
