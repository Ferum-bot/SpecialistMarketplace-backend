package com.github.ferumbot.specmarket.exceptions

data class ProfessionWasNotDeleted(
    val reason: String = ""
): IllegalArgumentException("Profession was not deleted")