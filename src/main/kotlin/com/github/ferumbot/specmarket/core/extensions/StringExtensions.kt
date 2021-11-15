package com.github.ferumbot.specmarket.core.extensions

fun String.removeFirstCharIf(condition: (String) -> Boolean): String {
    return if (condition.invoke(this)) {
        substring(1)
    } else {
        this
    }
}