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
            UserInputInvalidDataScreenState.screenName -> UserInputInvalidDataScreenState
            UserInputFullNameScreenState.screenName -> UserInputFullNameScreenState
            UserInputDepartmentScreenState.screenName -> UserInputDepartmentScreenState
            UserInputProfessionScreenState.screenName -> UserInputProfessionScreenState
            UserInputKeySkillsScreenState.screenName -> UserInputKeySkillsScreenState
            UserInputPortfolioLinkScreenState.screenName -> UserInputPortfolioLinkScreenState
            UserInputAboutMeScreenState.screenName -> UserInputAboutMeScreenState
            UserInputWorkingConditionsScreenState.screenName -> UserInputWorkingConditionsScreenState
            UserInputEducationGradeScreenState.screenName -> UserInputEducationGradeScreenState
            UserInputContactLinksScreenState.screenName -> UserInputContactLinksScreenState
            ShowProfilePreviewScreenState.screenName -> ShowProfilePreviewScreenState
            else -> UnSupportedScreenState
        }
    }
}