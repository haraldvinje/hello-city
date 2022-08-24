package com.example.hellocity

import com.example.hellocity.repositories.CityRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CityConfiguration {

    @Bean
    fun databaseInitializer(cityRepository: CityRepository) = ApplicationRunner {}
}
