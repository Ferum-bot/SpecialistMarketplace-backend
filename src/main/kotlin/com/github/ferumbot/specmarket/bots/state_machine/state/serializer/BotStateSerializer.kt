package com.github.ferumbot.specmarket.bots.state_machine.state.serializer

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState

class BotStateSerializer(
    state: Class<BotState>?
): StdSerializer<BotState>(state) {

    constructor() : this(null)

    override fun serialize(state: BotState?, generator: JsonGenerator?, provider: SerializerProvider?) {
        generator?.writeString(state?.screenName)
    }
}