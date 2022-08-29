package com.example.hellocity.models

import com.example.hellocity.extensions.toSlug
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Pattern

@Table(name = "cities")
@Entity
class City(
    var name: String,
    var description: String,
    var slug: String = name.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy = GenerationType.TABLE) var id: Long = 0
)

data class NewCity(
    @get:Pattern(regexp = "^[a-zA-Z\\s-]+")
    var name: String,
    var description: String,
)
