package com.example.hellocity.extensions

import java.util.Locale
import java.util.Optional

fun String.toSlug() =
    lowercase(Locale.getDefault())
        .replace("\n", " ")
        .replace("[^a-z\\d\\s]".toRegex(), " ")
        .split(" ")
        .joinToString("-")
        .replace("-+".toRegex(), "-")

fun <T> Optional<T>.unwrap(): T? = orElse(null)
