package com.example.hellocity

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HelloCityApplication

fun main(args: Array<String>) {
    runApplication<HelloCityApplication>(*args)
}
