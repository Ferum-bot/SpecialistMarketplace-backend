package com.github.ferumbot.specmarket.core.extensions

import java.util.*

inline fun <reified T> Optional<T>.getOrNull(): T? {
    return orElseGet { null }
}