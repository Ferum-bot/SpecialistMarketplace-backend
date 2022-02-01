package com.github.ferumbot.specmarket.exceptions

data class NicheNotExists(
    val reason: String? = null
): IllegalArgumentException("Niche doesn't exists")