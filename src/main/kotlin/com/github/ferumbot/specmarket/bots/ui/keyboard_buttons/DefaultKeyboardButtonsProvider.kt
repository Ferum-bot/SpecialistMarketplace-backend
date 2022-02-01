package com.github.ferumbot.specmarket.bots.ui.keyboard_buttons

import com.github.ferumbot.specmarket.bots.state_machine.event.*
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow

class DefaultKeyboardButtonsProvider: KeyboardMessageButtonsProvider {

    companion object {

        private val ALL_SPECIALIST_NAME = OpenAllSpecialistsScreenEvent.friendlyName
        private val I_AM_CUSTOMER_NAME = OpenIAmCustomerScreenEvent.friendlyName
        private val I_AM_SPECIALIST_NAME = OpenIAmSpecialistScreenEvent.friendlyName
        private val MY_PROFILE_NAME = OpenMyProfileScreenEvent.friendlyName
        private val CONTACT_WITH_US_NAME = OpenContactWithUsScreenEvent.friendlyName

        private val SUBMIT_MY_CV_NAME = SubmitYourCVEvent.friendlyName

        private val OPEN_FILTER_NAME = OpenFilterScreenEvent.friendlyName
        private val OPEN_I_DO_NOT_WHO_I_SEARCH_NAME = OpenIDoNotKnowWhoISearchScreenEvent.friendlyName

        private val OPEN_I_DO_KNOW_WHAT_I_WANT_NAME = OpenIDoNotKnowWhatIWantScreenEvent.friendlyName
        private val OPEN_ABOUT_EACH_SPECIALIST_NAME = OpenAboutEachSpecialistScreenEvent.friendlyName

        private val OPEN_MY_REQUESTS = OpenMyRequestsScreenEvent.friendlyName
        private val START_REGISTRATION = StartRegistrationFlowEvent.friendlyName
        private val CONTINUE_CREATING_PROFILE = ContinueCreatingProfileFlowEvent.friendlyName
        private val EDIT_PROFILE = OpenEditInfoScreenEvent.friendlyName
        private val CHANGE_VISIBILITY = ChangeProfileSpecialistVisibilityScreenEvent.friendlyName

        private val CHANGE_FULL_NAME = ChangeFullNameEvent.friendlyName
        private val CHANGE_DEPARTMENT = ChangeNicheEvent.friendlyName
        private val CHANGE_PROFESSIONS = ChangeProfessionEvent.friendlyName
        private val CHANGE_KEY_SKILLS = ChangeKeySkillsEvent.friendlyName
        private val CHANGE_PORTFOLIO_LINK = ChangePortfolioLinkEvent.friendlyName
        private val CHANGE_ABOUT_ME = ChangeAboutMeEvent.friendlyName
        private val CHANGE_WORKING_CONDITIONS = ChangeWorkingConditionsEvent.friendlyName
        private val CHANGE_EDUCATION_GRADE = ChangeEducationGradeEvent.friendlyName
        private val CHANGE_CONTACT_LINKS = ChangeContactLinksEvent.friendlyName

        private val FINISH_EDITING_PROFILE = FinishProfileEditingEvent.friendlyName

        private val RESTART_REGISTRATION = RestartRegistrationFlowEvent.friendlyName
        private val FINISH_REGISTRATION = OnUserRegistrationFinishedEvent.friendlyName

        private val LEAVE_BID_NAME = OpenLeaveBidScreenEvent.friendlyName
    }

    override fun provideStartScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(ALL_SPECIALIST_NAME)
        val secondRow = getRowWithButtons(I_AM_CUSTOMER_NAME, I_AM_SPECIALIST_NAME)
        val thirdRow = getRowWithButtons(MY_PROFILE_NAME)
        val forthRow = getRowWithButtons(CONTACT_WITH_US_NAME)

        return getKeyBoardWithRows(firstRow, secondRow, thirdRow, forthRow)
    }

    override fun provideAllSpecialistScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(OPEN_FILTER_NAME)
        val secondRow = getRowWithButtons(OPEN_I_DO_NOT_WHO_I_SEARCH_NAME)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun provideIAmCustomerInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(OPEN_I_DO_KNOW_WHAT_I_WANT_NAME)
        val secondRow = getRowWithButtons(OPEN_ABOUT_EACH_SPECIALIST_NAME)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun provideIAmSpecialistInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(MY_PROFILE_NAME)
        val secondRow = getRowWithButtons(SUBMIT_MY_CV_NAME)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun provideMyCVInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(MY_PROFILE_NAME)

        return getKeyBoardWithRows(firstRow)
    }

    override fun provideIDoNotKnowWhoISearchButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(LEAVE_BID_NAME)
        val secondRow = getRowWithButtons(OPEN_FILTER_NAME)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun provideNotAuthorizedInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(START_REGISTRATION)
        val secondRow = getRowWithButtons(OPEN_MY_REQUESTS)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun providePartiallyAuthorizedInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(CONTINUE_CREATING_PROFILE)
        val secondRow = getRowWithButtons(OPEN_MY_REQUESTS)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    override fun provideAuthorizedInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(EDIT_PROFILE)
        val secondRow = getRowWithButtons(OPEN_MY_REQUESTS)
        val thirdRow = getRowWithButtons(CHANGE_VISIBILITY)

        return getKeyBoardWithRows(firstRow, secondRow, thirdRow)
    }

    override fun provideEditProfileInfoScreenButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(CHANGE_FULL_NAME, CHANGE_DEPARTMENT)
        val secondRow = getRowWithButtons(CHANGE_PROFESSIONS, CHANGE_KEY_SKILLS)
        val thirdRow = getRowWithButtons(CHANGE_PORTFOLIO_LINK, CHANGE_ABOUT_ME)
        val forthRow = getRowWithButtons(CHANGE_WORKING_CONDITIONS, CHANGE_EDUCATION_GRADE)
        val fivesRow = getRowWithButtons(CHANGE_CONTACT_LINKS)
        val sixRow = getRowWithButtons(FINISH_EDITING_PROFILE)

        return getKeyBoardWithRows(firstRow, secondRow, thirdRow, forthRow, fivesRow, sixRow)
    }

    override fun provideShowProfileButtons(): ReplyKeyboardMarkup {
        val firstRow = getRowWithButtons(FINISH_REGISTRATION)
        val secondRow = getRowWithButtons(RESTART_REGISTRATION)

        return getKeyBoardWithRows(firstRow, secondRow)
    }

    private fun getDefaultKeyboardMarkup() = ReplyKeyboardMarkup()
    .apply {
        selective = true
        resizeKeyboard = true
        oneTimeKeyboard = true
    }

    private fun getRowWithButtons(vararg buttonTexts: String): KeyboardRow {
        val row = KeyboardRow()
        buttonTexts.forEach { text ->
            val button = KeyboardButton(text)
            row.add(button)
        }
        return row
    }

    private fun getKeyBoardWithRows(vararg rows: KeyboardRow): ReplyKeyboardMarkup {
        val keyboardMarkup = getDefaultKeyboardMarkup()
        val keyboard = mutableListOf<KeyboardRow>()
        rows.forEach { row ->
            keyboard.add(row)
        }
        keyboardMarkup.keyboard = keyboard
        return keyboardMarkup
    }
}