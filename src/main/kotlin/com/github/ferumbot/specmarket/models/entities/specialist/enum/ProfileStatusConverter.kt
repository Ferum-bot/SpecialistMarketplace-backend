package com.github.ferumbot.specmarket.models.entities.specialist.enum

import com.github.ferumbot.specmarket.exceptions.UndefinedProfileStatus
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class ProfileStatusConverter: Converter<String, ProfileStatuses> {

    override fun convert(source: String): ProfileStatuses {
        val result = if (ProfileStatuses.isPresented(source)) {
            ProfileStatuses.fromAlias(source)
        } else {
            throw UndefinedProfileStatus("Undefined status: $source")
        }

        return result
    }

}