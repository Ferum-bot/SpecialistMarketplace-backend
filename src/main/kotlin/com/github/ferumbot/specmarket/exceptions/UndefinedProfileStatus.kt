package com.github.ferumbot.specmarket.exceptions

data class UndefinedProfileStatus(
    val reason: String? = null
): IllegalArgumentException("Undefined profile status")
