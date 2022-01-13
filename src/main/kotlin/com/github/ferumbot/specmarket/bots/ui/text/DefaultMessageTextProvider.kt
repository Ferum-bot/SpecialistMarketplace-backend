package com.github.ferumbot.specmarket.bots.ui.text

import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenCurrentSpecialistsScreenEvent
import com.github.ferumbot.specmarket.core.wrappers.EmojiWrapper
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import kotlin.text.StringBuilder

class DefaultMessageTextProvider: MessageTextProvider {

    override fun provideStartScreenMessage(): String {
        return StringBuilder()
            .append("Привет!\n")
            .append("\n")
            .append("Этот бот поможет тебе найти специалиста из сферы SMM для любого твоего проекта.\n")
            .append("Если ты хочешь найти специалиста, нажимай \"я заказчик\".\n")
            .append("Если ты ищешь заказы, нажимай \"я специалист\".")
            .toString()
    }

    override fun provideContactWithUsMessage(): String {
        return StringBuilder()
            .append("Вопросы по работе бота и баги: @dr_matjo\n")
            .append("Если возникли трудности с исполнителем или заказчиком: @ma_popovv\n")
            .toString()
    }

    override fun provideNotImplementedMessage(): String {
        return StringBuilder()
            .append("Упс! Этот экран еще в разработке. Попробуйте позже.\n")
            .toString()
    }

    override fun provideUnSupportedMessage(): String {
        return StringBuilder()
            .append("Неверная комманда!\n")
            .append("Пожалуйста, используйте только кнопки на клавиатуры.\n")
            .toString()
    }

    override fun provideIAmCustomerInfoMessage(): String {
        return StringBuilder()
            .append("Отлично! Давайте найдем вам подходящих специалистов.\n")
            .append("Знаете ли вы, кто конкретно вам нужен для ваших задач?\n")
            .toString()
    }

    override fun provideIDoNotKnowWhatIWantMessage(): String {
        return StringBuilder()
            .append("Если вы не знаете, какой специалист вам нужен, вы можете:\n")
            .append("\n")
            .append("1) Воспользоваться нашей платной услугой подбора специалистов.")
            .append("Мы свяжем вас с нашим продюсером, который проконсультирует вас и подберет исполнителей под ваши нужды и бюджет.")
            .append("\n")
            .append("2) Почитать подробнее про профессии в SMM в нашей справке.")
            .append("Так вы сможете лучше понять, что из себя представляет каждая профессия и кто нужен именно вам.")
            .toString()

    }

    override fun provideIDoNotKnowWhoISearchMessage(): String {
        return StringBuilder()
            .append("Если вы не знаете, какой специалист вам нужен, вы можете:\n")
            .append("\n")
            .append("1) Воспользоваться нашей платной услугой подбора специалистов.")
            .append("Мы свяжем вас с нашим продюсером, который проконсультирует вас и подберет исполнителей под ваши нужды и бюджет.")
            .append("\n")
            .append("2) Почитать подробнее про профессии в SMM в нашей справке.")
            .append("Так вы сможете лучше понять, что из себя представляет каждая профессия и кто нужен именно вам.")
            .toString()
    }

    override fun provideLeaveBidInfoMessage(): String {
        return StringBuilder()
            .append("Чтобы оставить заявку на подбор специалиста, пожалуйста, напишите @owinowaa и мы вам сразу же ответим!\n")
            .toString()
    }

    override fun provideAboutEachSpecialistMessage(professions: Collection<ProfessionDto>): String {
        val command = OpenCurrentSpecialistsScreenEvent.commandAlias
        val professionsToShow = professions.fold(StringBuilder()) { builder, profession ->
            builder.append("*${profession.friendlyName} - ${profession.longDescription ?: profession.shortDescription}\n")
            builder.append("Подробнее: ${command}_${profession.alias}\n")
        }

        return StringBuilder()
            .append("Список всех доступных специалистов:\n")
            .append("\n")
            .append(professionsToShow)
            .toString()
    }

    override fun provideIAmSpecialistInfoMessage(): String {
        return StringBuilder()
            .append("Отлично! Давайте разместим вашу вакансию в сервисе.\n")
            .toString()
    }

    override fun provideMyCVInfoMessage(): String {
        return StringBuilder()
            .append("Чтобы загрузить ваше резюме, авторизуйтесь как специалист,\n")
            .append("и затем смените видимость вашего профиля на \"Видимый\".\n")
            .toString()
    }

    override fun provideAllSpecialistInfoMessage(): String {
        return StringBuilder()
            .append("Вы можете использовать фильтры для поиска специалистов")
            .append("или воспользоваться нашей помощью, если не знаете, кто конкретно вам нужен\n")
            .toString()
    }

    override fun provideFilterScreenInfoMessage(professions: Collection<ProfessionDto>): String {
        val command = OpenCurrentSpecialistsScreenEvent.commandAlias
        val professionsToFilter = professions.fold(StringBuilder()) { builder, profession ->
            builder.append("${profession.friendlyName}: ${profession.shortDescription}\n")
            builder.append("Отфильтровать по этой профессии ${command}_${profession.alias}\n")
        }

        return StringBuilder()
            .append("Вы можете отфильтровать специалистов по их профессиям!\n")
            .append("Доступные профессии:\n")
            .append("\n")
            .append(professionsToFilter)
            .toString()
    }

    override fun provideCurrentSpecialistsInfoMessage(specialists: Collection<SpecialistDto>): String {
        val currentSpecialists = specialists.fold(StringBuilder()) { builder, specialist ->
            builder.append(getProfileTemplate(specialist))
        }

       return StringBuilder()
           .append("Найденные специалисты:\n")
           .append("\n")
           .append(currentSpecialists)
           .toString()
    }

    override fun provideCurrentSpecialistsEmptyInfoMessage(): String {
        return StringBuilder()
            .append("Упс! Мы не нашли таких специалистов... Пожалуйста, поменяйте фильтры и попробуйте еще раз!\n")
            .toString()
    }

    override fun provideCurrentSpecialistContactsInfoMessage(contacts: String): String {
        return StringBuilder()
            .append("Контакты специалиста:\n")
            .append(contacts)
            .toString()
    }

    override fun provideYouAreNotAuthorizedInfoMessage(): String {
        return StringBuilder()
            .append("Профиль\n")
            .append("\n")
            .append("Вы не авторизованы!\n")
            .append("Вы можете просматривать ваши заказы!\n")
            .toString()
    }

    override fun provideYouArePartiallyAuthorizedInfoMessage(specialist: SpecialistDto): String {
        return StringBuilder()
            .append("Профиль\n")
            .append("\n")
            .append("Вы не окончили процесс регистрации до конца, но можете продолжить в любой момент.\n")
            .append("Теперь ваш профиль выглядит так:\n")
            .append(getProfileTemplate(specialist))
            .append("Вы можете просматривать ваши заказы!\n")
            .toString()
    }

    override fun provideYouAreAuthorizedInfoMessage(specialist: SpecialistDto): String {
        val isVisible = StringBuilder()
        if (specialist.isVisible) {
            isVisible.append("Ваш профиль виден заказчикам!\n")
        } else {
            isVisible.append("Ваш профиль НЕ виден заказчикам!\n")
        }

        return StringBuilder()
            .append("Профиль\n")
            .append("\n")
            .append(getProfileTemplate(specialist))
            .append("Вы можете просматривать ваши заказы!\n")
            .append(isVisible)
            .toString()
    }

    override fun provideSpecialistRequestInfoMessage(specialists: Collection<SpecialistDto>): String {
        if (specialists.isEmpty()) {
            return StringBuilder()
                .append("На данный момент вы не оставили ни одной заявки")
                .toString()
        }

        val specialistsString = specialists.fold(StringBuilder()) { builder, specialist ->
            builder
                .append(getProfileTemplate(specialist))
                .append("\n")
        }
        return StringBuilder()
            .append("Ваши заявки:\n")
            .append("\n")
            .append(specialistsString)
            .toString()
    }

    override fun provideEditProfileInfoMessage(specialist: SpecialistDto): String {
        return StringBuilder()
            .append("Теперь ваш профиль выглядит так:\n")
            .append("\n")
            .append(getProfileTemplate(specialist))
            .append("Вы можете настроить любой параметр\n")
            .toString()
    }

    override fun provideUserInputInvalidDataMessage(): String {
        return StringBuilder()
            .append("Некорректные данные! Пожалуйста, попробуйте еще раз.\n")
            .toString()
    }

    override fun provideUserInputFullNameInfoMessage(): String {
        return StringBuilder()
            .append("Начнем с имени. Как вас зовут?\n")
            .append(userInputFullNameTemplate())
            .toString()
    }

    override fun provideUserInputDepartmentInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваш отдел\n")
            .append(userInputDepartmentTemplate())
            .toString()
    }

    override fun provideUserInputProfessionsInfoMessage(availableProfessions: Collection<ProfessionDto>): String {
        return StringBuilder()
            .append("Выберите свою профессию\n")
            .append(userInputProfessionTemplate(availableProfessions))
            .toString()
    }

    override fun provideUserInputKeySkillsInfoMessage(): String {
        return StringBuilder()
            .append("Опишите ваши умения\n")
            .append(userInputKeySkillsTemplate())
            .toString()
    }

    override fun provideUserInputPortfolioLinkInfoMessage(): String {
        return StringBuilder()
            .append("Отправьте ссылку на ваше портфолио\n")
            .append(userInputPortfolioLinkTemplate())
            .toString()
    }

    override fun provideUserInputAboutMeInfoMessage(): String {
        return StringBuilder()
            .append("Напишите немного о себе\n")
            .append(userInputAboutMeTemplate())
            .toString()
    }

    override fun provideUserInputWorkingConditionsInfoMessage(): String {
        return StringBuilder()
            .append("Опишите ваши условия работы\n")
            .append(userInputWorkingConditionsTemplate())
            .toString()
    }

    override fun provideUserInputEducationGradeInfoMessage(): String {
        return StringBuilder()
            .append("Опишите ваше образование\n")
            .append(userInputEducationGradeTemplate())
            .toString()
    }

    override fun provideUserInputContactLinksInfoMessage(): String {
        return StringBuilder()
            .append("Напишите свои контакты для связи\n")
            .append(userInputContactLinksTemplate())
            .toString()
    }

    override fun provideHowProfileLooksNowMessage(profile: SpecialistDto): String {
        return StringBuilder()
            .append("Теперь ваш профиль выглядит так:\n")
            .append("\n")
            .append(getProfileTemplate(profile))
            .append("Продолжить регистрацию?")
            .toString()
    }

    override fun provideProfilePreviewMessage(profile: SpecialistDto): String {
        return StringBuilder()
            .append("Отлично! Вы ввели всю необходимую информацию.\n")
            .append("Это ваш профиль:\n")
            .append("\n")
            .append(getProfileTemplate(profile))
            .append("If you finish registration, you profile will automatically become visible to all customers\n")
            .append("Когда вы закончите регистрацию, ваш профиль автоматически будет виден всем заказчикам.\n")
            .append("Вы можете поменять видимость профиля и информацию о себе в любое время!\n")
            .toString()
    }

    override fun provideUserChangeFullNameInfoMessage(): String {
        return StringBuilder()
            .append("Введите имя и фамилию\n")
            .append(userInputFullNameTemplate())
            .toString()
    }

    override fun provideUserChangeDepartmentInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваш отдел\n")
            .append(userInputDepartmentTemplate())
            .toString()
    }

    override fun provideUserChangeProfessionsInfoMessage(professions: Collection<ProfessionDto>): String {
        return StringBuilder()
            .append("Введите вашу профессию\n")
            .append(userInputProfessionTemplate(professions))
            .toString()
    }

    override fun provideUserChangeKeySkillsInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваши навыки\n")
            .append(userInputKeySkillsTemplate())
            .toString()
    }

    override fun provideUserChangePortfolioLinkInfoMessage(): String {
        return StringBuilder()
            .append("Укажите ссылку на ваше портфолио\n")
            .append(userInputPortfolioLinkTemplate())
            .toString()
    }

    override fun provideUserChangeAboutMeInfoMessage(): String {
        return StringBuilder()
            .append("Введите информацию о себе\n")
            .append(userInputAboutMeTemplate())
            .toString()
    }

    override fun provideUserChangeWorkingConditionsInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваши условия работы\n")
            .append(userInputWorkingConditionsTemplate())
            .toString()
    }

    override fun provideUserChangeEducationGradeInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваше образование\n")
            .append(userInputEducationGradeTemplate())
            .toString()
    }

    override fun provideUserChangeContactLinksInfoMessage(): String {
        return StringBuilder()
            .append("Введите вашу контактную информацию\n")
            .append(userInputContactLinksTemplate())
            .toString()
    }

    private fun userInputFullNameTemplate(): StringBuilder {
        return StringBuilder()
            .append("Введите имя и фамилию\n")
            .append("Пример: Иван Иванов")
    }

    private fun userInputDepartmentTemplate(): StringBuilder {
        return StringBuilder()
            .append("Пример: Автомобили, Инстаграм")
    }

    private fun userInputProfessionTemplate(professions: Collection<ProfessionDto>): StringBuilder {
        val professionsInfo = professions.fold(StringBuilder()) { builder, profession ->
            builder.append("${profession.friendlyName}: ${profession.shortDescription}\n")
            builder.append("Выберите /${profession.alias} \n")
        }

        return StringBuilder()
            .append("Вам доступны ${professions.count()} профессий!\n")
            .append(professionsInfo)
            .append("Выберите нужную вам!\n")
    }

    private fun userInputKeySkillsTemplate(): StringBuilder {
        return StringBuilder()
            .append("Например: упорство, креатив\n")
    }

    private fun userInputPortfolioLinkTemplate(): StringBuilder {
        return StringBuilder()
            .append("Опишите ваши кейсы, опыт работы и т.д.")
    }

    private fun userInputAboutMeTemplate(): StringBuilder {
        return StringBuilder()
            .append("Это очень важно для ваших будущих заказчиков!\n")
            .append("Наример: Я работаю в этой индустрии более трех лет")
    }

    private fun userInputWorkingConditionsTemplate(): StringBuilder {
        return StringBuilder()
            .append("Например: от 500$ + процент")
    }

    private fun userInputEducationGradeTemplate(): StringBuilder {
        return StringBuilder()
            .append("Например: Закончил курсы по SMM")
    }

    private fun userInputContactLinksTemplate(): StringBuilder {
        return StringBuilder()
            .append("Укажите как можно больше ссылок\n")
            .append("Мы покажем это вашим потенциальным заказчикам\n")
            .append("Например: TG: ..., VK: ...")
    }

    private fun getProfileTemplate(specialist: SpecialistDto, ignoreContactLinks: Boolean = false): StringBuilder {
        return specialist.run {
            StringBuilder()
                .append("1. Имя и фамилия:\n ${fullName ?: "-"} \n")
                .append("2. Отдел:\n ${department ?: "-"} \n")
                .append("3. Профессии:\n ${professions.joinToString().ifEmpty { "-" }} \n")
                .append("4. Навыки:\n ${keySkills.joinToString().ifEmpty { "-" }} \n")
                .append("5. Ссылка на портфолио:\n ${portfolioLink ?: "-"} \n")
                .append("6. Обо мне:\n ${aboutMe ?: "-"} \n")
                .append("7. Условия работы:\n ${workingConditions ?: "-"} \n")
                .append("8. Образование:\n ${educationGrade ?: "-"} \n")
                .apply {
                    if (!ignoreContactLinks) {
                        append("9. Контакты:\n ${contactLinks ?: "-"} \n")
                    }
                }
                .append("\n")

        }
    }
}