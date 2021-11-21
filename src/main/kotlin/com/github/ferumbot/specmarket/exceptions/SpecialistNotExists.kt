package com.github.ferumbot.specmarket.exceptions

import java.lang.IllegalArgumentException

data class SpecialistNotExists(
    val id: Long,
): IllegalArgumentException("Specialist with id: $id doesn't exists!")
