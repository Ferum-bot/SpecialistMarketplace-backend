package com.github.ferumbot.specmarket.bots.state_machine.configs

import com.github.ferumbot.specmarket.bots.state_machine.event.BotEvent
import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import org.springframework.context.annotation.Configuration
import org.springframework.statemachine.config.EnableStateMachineFactory
import org.springframework.statemachine.config.StateMachineConfigurerAdapter
import org.springframework.statemachine.config.builders.*

@Configuration
@EnableStateMachineFactory
class BotStateMachineConfig: StateMachineConfigurerAdapter<BotState, BotEvent>() {

    override fun configure(config: StateMachineConfigBuilder<BotState, BotEvent>?) {
        config ?: return
    }

    override fun configure(model: StateMachineModelConfigurer<BotState, BotEvent>?) {
        model ?: return
    }

    override fun configure(config: StateMachineConfigurationConfigurer<BotState, BotEvent>?) {
        config ?: return
    }

    override fun configure(states: StateMachineStateConfigurer<BotState, BotEvent>?) {
        states ?: return
    }

    override fun configure(transitions: StateMachineTransitionConfigurer<BotState, BotEvent>?) {
        transitions ?: return
    }
}