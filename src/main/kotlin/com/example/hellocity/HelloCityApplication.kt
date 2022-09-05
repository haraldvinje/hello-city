package com.example.hellocity

import com.example.hellocity.models.City
import com.example.hellocity.repositories.CityRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@SpringBootApplication
class HelloCityApplication

fun main(args: Array<String>) {
    runApplication<HelloCityApplication>(*args)
}

@Component
class DataInitializer(private val cityRepository: CityRepository) {
    @EventListener(value = [ApplicationReadyEvent::class])
    fun init() {
        cityRepository.saveAll(
            listOf(
                City("London", "Capital of UK"),
                City("Oslo", "Capital of Norway")
            )
        )
    }
}
