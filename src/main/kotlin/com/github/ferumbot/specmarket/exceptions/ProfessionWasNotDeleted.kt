package com.github.ferumbot.specmarket.exceptions

data class ProfessionWasNotDeleted(
    val reason: String? = null,
): IllegalArgumentException("Profession was not deleted")