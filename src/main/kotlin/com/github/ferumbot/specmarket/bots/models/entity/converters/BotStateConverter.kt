package com.github.ferumbot.specmarket.bots.models.entity.converters

import com.github.ferumbot.specmarket.bots.state_machine.machine.StateMachine
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnRegisteredState
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter
class BotStateConverter: AttributeConverter<BotState, String?> {

    override fun convertToDatabaseColumn(attribute: BotState?): String? {
        return attribute?.screenName
    }

    override fun convertToEntityAttribute(dbData: String?): BotState {
        dbData ?: return UnRegisteredState
        return StateMachine.getStateByName(dbData)
    }
}