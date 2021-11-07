package com.github.ferumbot.specmarket.bots.state_machine.machine

import com.github.ferumbot.specmarket.bots.state_machine.state.*

object StateMachine {

    fun getStateByName(stateName: String): BotState {
        return when(stateName) {
            NotImplementedScreenState.screenName -> NotImplementedScreenState
            UnSupportedScreenState.screenName -> UnSupportedScreenState
            StartScreenState.screenName -> StartScreenState
            ContactWithUsScreenState.screenName -> ContactWithUsScreenState
            UnRegisteredState.screenName -> UnRegisteredState
            AllSpecialistInfoScreenState.screenName -> AllSpecialistInfoScreenState
            IDoNotKnowWhoISearchScreenState.screenName -> IDoNotKnowWhoISearchScreenState
            LeaveBidInfoScreenState.screenName -> LeaveBidInfoScreenState
            IAmCustomerInfoScreenState.screenName -> IAmCustomerInfoScreenState
            IDoNotKnowWhatIWantScreenState.screenName -> IDoNotKnowWhatIWantScreenState
            AboutEachSpecialistScreenState.screenName -> AboutEachSpecialistScreenState
            IAmSpecialistInfoScreenState.screenName -> IAmSpecialistInfoScreenState
            else -> UnSupportedScreenState
        }
    }
}