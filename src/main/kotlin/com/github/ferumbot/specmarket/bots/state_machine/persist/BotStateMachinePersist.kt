package com.github.ferumbot.specmarket.bots.state_machine.persist

import com.github.ferumbot.specmarket.bots.state_machine.event.BotEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.statemachine.StateMachineContext
import org.springframework.statemachine.StateMachinePersist
import org.springframework.stereotype.Component

@Component
class BotStateMachinePersist @Autowired constructor(

): StateMachinePersist<BotState, BotEvent, String> {

    override fun write(context: StateMachineContext<BotState, BotEvent>, contextObj: String) {

    }

    override fun read(contextObj: String): StateMachineContext<BotState, BotEvent> {
        TODO("Not yet implemented")
    }
}