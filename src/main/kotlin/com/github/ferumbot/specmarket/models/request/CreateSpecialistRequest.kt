package com.github.ferumbot.specmarket.models.request

import com.github.ferumbot.specmarket.core.ConstraintValidationMessages
import com.github.ferumbot.specmarket.core.ConstraintValidationMessages.NOT_BLANK_MESSAGE
import com.github.ferumbot.specmarket.core.ConstraintValidationMessages.SIZE_MESSAGE
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class CreateSpecialistRequest(

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(min = 4, max = 100, message = SIZE_MESSAGE)
    val fullName: String,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(min = 1, max = 5, message = SIZE_MESSAGE)
    val professionIds: Collection<Long>,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(min = 1, max = 10, message = SIZE_MESSAGE)
    val nichesIds: Collection<Long>,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(min = 2, max = 5, message = SIZE_MESSAGE)
    val keySkills: Collection<String>,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(max = 500, message = SIZE_MESSAGE)
    val portfolioLink: String,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(max = 1000, message = SIZE_MESSAGE)
    val aboutMe: String,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(max = 100, message = SIZE_MESSAGE)
    val workingConditions: String,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(max = 300, message = SIZE_MESSAGE)
    val educationGrade: String,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(max = 300, message = SIZE_MESSAGE)
    val contactLinks: String,

    val isVisible: Boolean = false,
)
