package com.github.ferumbot.specmarket.core.extensions

fun Collection<String>.firstOrEmpty() = firstOrNull().orEmpty()