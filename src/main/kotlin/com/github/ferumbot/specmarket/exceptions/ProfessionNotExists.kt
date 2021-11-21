package com.github.ferumbot.specmarket.exceptions

data class ProfessionNotExists(
    val reason: String? = null,
): IllegalArgumentException("Profession doesn't exists")
