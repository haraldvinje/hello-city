package com.example.hellonorway

import java.time.LocalDateTime
import javax.persistence.*

@Table(name = "cities")
@Entity
class City(
    var name: String,
    var description: String,
    var slug: String = name.toSlug(),
    var addedAt: LocalDateTime = LocalDateTime.now(),
    @Id @GeneratedValue(strategy= GenerationType.IDENTITY) var id: Long = 0
)

data class NewCity(
    var name: String,
    var description: String,
)

data class Message(val id: String?, val text: String)
