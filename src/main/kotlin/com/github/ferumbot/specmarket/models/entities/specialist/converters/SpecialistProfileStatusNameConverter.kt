package com.github.ferumbot.specmarket.models.entities.specialist.converters

import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses.NOT_FILLED
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class SpecialistProfileStatusNameConverter: AttributeConverter<ProfileStatuses, String> {

    override fun convertToDatabaseColumn(attribute: ProfileStatuses?): String {
        return attribute?.alias ?: NOT_FILLED.alias
    }

    override fun convertToEntityAttribute(dbData: String?): ProfileStatuses {
        val statuses = ProfileStatuses.values()
        for (status in statuses) {
            if (status.alias == dbData) {
                return status
            }
        }
        return NOT_FILLED
    }
}