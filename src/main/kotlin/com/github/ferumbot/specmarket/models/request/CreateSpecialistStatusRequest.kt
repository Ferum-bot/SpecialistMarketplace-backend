package com.github.ferumbot.specmarket.models.request

import com.github.ferumbot.specmarket.core.ConstraintValidationMessages
import com.github.ferumbot.specmarket.core.ConstraintValidationMessages.NOT_BLANK_MESSAGE
import com.github.ferumbot.specmarket.core.ConstraintValidationMessages.SIZE_MESSAGE
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateSpecialistStatusRequest(

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(min = 4, max = 30, message = SIZE_MESSAGE)
    val alias: String,

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(min = 1, max = 100, message = SIZE_MESSAGE)
    val name: String,

)
