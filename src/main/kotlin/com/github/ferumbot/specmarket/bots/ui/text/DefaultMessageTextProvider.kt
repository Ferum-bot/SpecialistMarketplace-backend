package com.github.ferumbot.specmarket.bots.ui.text

import com.github.ferumbot.specmarket.bots.models.dto.update_info.UserSpecialistInfo
import com.github.ferumbot.specmarket.bots.state_machine.event.OpenCurrentSpecialistsScreenEvent
import com.github.ferumbot.specmarket.core.wrappers.EmojiWrapper
import com.github.ferumbot.specmarket.models.dto.NicheDto
import com.github.ferumbot.specmarket.models.dto.ProfessionDto
import com.github.ferumbot.specmarket.models.dto.SpecialistDto
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses.*
import kotlin.text.StringBuilder

class DefaultMessageTextProvider: MessageTextProvider {

    override fun provideStartScreenMessage(): String {
        return StringBuilder()
            .append("Это главыный экран\n")
            .append("\n")
            .append("Этот бот поможет тебе найти специалиста из сферы SMM для любого твоего проекта.\n")
            .append("Если ты хочешь найти специалиста, нажимай \"я заказчик\".\n")
            .append("Если ты ищешь заказы, нажимай \"я специалист\".")
            .toString()
    }

    override fun provideContactWithUsMessage(): String {
        return StringBuilder()
            .append("Если хочешь связаться с нами, то вот несколько контактов.\n\n")
            .append("Сотрудничество: @Danverrr\n")
            .append("Если возникли трудности с исполнителем или заказчиком: @Danverrr\n")
            .append("Если увидел какую-то ошибку или неточность: @ma_popovv\n")
            .append("Для любых других вопросов или предложений можно писать в личку @Danverrr или @owinowaa")
            .toString()
    }

    override fun provideNotImplementedMessage(): String {
        return StringBuilder()
            .append("Данный экран еще не сделан.\n")
            .append("Попробуйте позже.\n")
            .toString()
    }

    override fun provideUnSupportedMessage(): String {
        return StringBuilder()
            .append("Не поддерживаемая команда.\n")
            .append("Пожалуйста, используйте только кнопки и следуйте инструкциям из сообщений!\n")
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
            .append("Если вы не знаете, какой специалист вам нужен, наш сервис может помочь вам!\n")
            .append("Мы предоставляем несколько возможностей для подбора специалистов:")
            .append("\n")
            .append("1. Вы можете воспользоваться нашей услугой подбора специалистов.")
            .append("Мы можем полностью освободить вас от поиска и первоначального общения со специалистами.")
            .append("Наши специалисты помогут вам правильно составить техническое задание, сформировать список требований и правильно составить договор.")
            .append("Мы сооринтируем вас по актуальным условиям оплаты и проведем интерьвю с кандидатами, таким образом вы получите")
            .append("только релевантных специалистов, которые идеально подойдут под ваш проект!\n")
            .append("Мы свяжем вас с нашим продюсером, который проконсультирует вас и подберет исполнителей под ваши нужды и бюджет.")
            .append("\n")
            .append("2. Вы можете воспользоваться нашей справкой, чтобы лучше понять, что из себя представляет каждый профессия")
            .append("и узнать кто нужен именно вам!\n")
            .toString()
    }

    override fun provideIDoNotKnowWhoISearchMessage(): String {
        return StringBuilder()
            .append("Если вы не знаете, какой специалист вам нужен, наш сервис может помочь вам!\n")
            .append("Мы предоставляем несколько возможностей для подбора специалистов:")
            .append("\n")
            .append("1. Вы можете воспользоваться нашей услугой подбора специалистов.")
            .append("Мы можем полностью освободить вас от поиска и первоначального общения со специалистами.")
            .append("Наши специалисты помогут вам правильно составить техническое задание, сформировать список требований и правильно составить договор.")
            .append("Мы сооринтируем вас по актуальным условиям оплаты и проведем интерьвю с кандидатами, таким образом вы получите")
            .append("только релевантных специалистов, которые идеально подойдут под ваш проект!\n")
            .append("Мы свяжем вас с нашим продюсером, который проконсультирует вас и подберет исполнителей под ваши нужды и бюджет.")
            .append("\n")
            .append("2. Вы можете воспользоваться нашей справкой, чтобы лучше понять, что из себя представляет каждый профессия")
            .append("и узнать кто нужен именно вам!\n")
            .toString()
    }

    override fun provideLeaveBidInfoMessage(): String {
        return StringBuilder()
            .append("Услуга подбора специалистов.\n\n")
            .append("Мы можем полностью освободить вас от поиска и первоначального общения со специалистами.")
            .append("Наши специалисты помогут вам правильно составить техническое задание, сформировать список требований и правильно составить договор.")
            .append("Мы сооринтируем вас по актуальным условиям оплаты и проведем интерьвю с кандидатами, таким образом вы получите")
            .append("только релевантных специалистов, которые идеально подойдут под ваш проект!\n\n")
            .append("Чтобы оставить заявку на подбор специалистов, напишите в личку @Danverrr")
            .toString()
    }

    override fun provideAboutEachSpecialistMessage(professions: Collection<ProfessionDto>): String {
        val professionsToShow = professions.fold(StringBuilder()) { builder, profession ->
            builder.append("*${profession.friendlyName} - ${profession.longDescription ?: profession.shortDescription}\n")
        }

        return StringBuilder()
            .append("Мы собрали для вас небольшую вики про каждого специалиста, ")
            .append("чтобы вы могли поподробнее узнать чем занимается специалист каждой профессии.\n")
            .append("На данный момент на нашей бирже присутствуют данные специалисты:\n")
            .append("\n")
            .append(professionsToShow)
            .toString()
    }

    override fun provideIAmSpecialistInfoMessage(): String {
        return StringBuilder()
            .append("Если вы являетесь специалистом, то можете разместить ваш профиль на нашей бирже.\n")
            .append("Наши специалисты проверят вашу заявку. Мы поможем вам правильно составать резюме.\n")
            .append("Мы укажем вам на недочеты, посоветуем как лучше все оформить и представить себя перед заказчиками.\n")
            .append("Блогадря такой системе, будет намного проще взаимодействовать с вашими заказчиками, так как они будут уверены")
            .append("что работают с профессионалом.\n")
            .append("Вы можете перейти на экран с профелем и начать получать заказы!\n")
            .toString()
    }

    override fun provideMyCVInfoMessage(): String {
        return StringBuilder()
            .append("Если вы хотите, чтобы мы разметили вашу заявку на нашей бирже, то необходимо пройти несколько шагов:\n")
            .append("1. В первую очередь нужно зарегистрироваться на нашей бирже.")
            .append("2. После этого, наши специалисты проверят ваш профиль. Мы поможем правильно составить ваше резюме, укажем на недочеты.\n")
            .append("3. Если ваше резюме пройдет модерацию(обычно это занимает пару часов), то теперь вы сможете управлять видимостью вашего профиля")
            .append("на бирже и получать релевантные заказы!\n")
            .append("Если вы что-то не поняли или знаете как сделать сервис лучше, то напишите @Danverrr.\n")
            .append("Мы всегда открыты к предложениям!\n")
            .append("и затем смените видимость вашего профиля на \"Видимый\".\n")
            .toString()
    }

    override fun provideAllSpecialistInfoMessage(): String {
        return StringBuilder()
            .append("Для того, чтобы начать поиск специалистов вы можете воспользоваться нашим удобным фильтром.\n")
            .append("На данный момент, сервис позволяет отфильтровать специалистов по двум категориям:\n")
            .append("1. По их профессии - например таргетолог, контентмейкер и т.д.\n")
            .append("2. По нише, в которой специалист имеет наиболее релевантный опыт - например психология, мотивация, бизнес и т.д.\n")
            .append("Для того, чтобы воспользоваться фильтром, нажмите соотвествующую кнопку.\n\n")
            .append("Если у вас есть некоторые затруднения в поисках специалистов или у вас просто нет времени, то ")
            .append("вы можете воспользоваться нашим сервисом!\n")
            .append("Мы подберем для вас качественных специалистов, проверим их портфолио и кейсы. Проведем собеседования, ")
            .append("чтобы убедиться, что именно этот специалист подходит под ваше техническое задание!\n")
            .append("Для этого перейдите на экран оставления заявки.")
            .toString()
    }

    override fun provideProfessionFilterScreenInfoMessage(professions: Collection<ProfessionDto>): String {
        val command = OpenCurrentSpecialistsScreenEvent.commandAlias
        val professionsToFilter = professions.fold(StringBuilder()) { builder, profession ->
            builder.append(profession.friendlyName)
            builder.append(" - Отфильтровать ${command}_${profession.alias}\n")
        }

        return StringBuilder()
            .append("Вы можете отфильтровать специалистов по их профессиям.\n")
            .append("Доступные профессии:\n")
            .append("\n")
            .append(professionsToFilter)
            .toString()
    }

    override fun provideNicheFilterScreenInfoMessage(niches: Collection<NicheDto>): String {
        val command = ""
        val nichesToFilter = niches.fold(StringBuilder()) { builder, niche ->
            builder.append(niche.friendlyName)
            builder.append(" - Отфильтровать ${command}_${niche.alias}\n")
        }

        return StringBuilder()
            .append("Теперь вы так же можете отфильтровать специалистов по нише в которой они работают.\n")
            .append("Если вы хотите посмотреть всех специалистов по данной профессии, то нажмите кнопку")
            .append(" \"Показать всех\"\n")
            .append("\n")
            .append(nichesToFilter)
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
            .append("К сожалению, доступных специалистов по такому профилю не найдено.\n")
            .append("Попробуйте ещё раз позже.\n")
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
            .append("У вас еще нет профиля специалиста!\n")
            .append("Если вы хотите, чтобы мы вас разместили на нашей бирже, то создайте свой профиль.\n")
            .append("После этого, наши специалисты проверят ваш профиль и вы будете видны на бирже.\n")
            .append("Вы можете просматривать свои обращения к другим специалистам!\n")
            .toString()
    }

    override fun provideYouArePartiallyAuthorizedInfoMessage(specialist: SpecialistDto): String {
        return StringBuilder()
            .append("Вы не окончили процесс регистрации до конца, но можете продолжить в любой момент.\n")
            .append("На данный момент ваш профиль выглядит так:\n")
            .append(getProfileTemplate(specialist))
            .append("Вы так же можете просмотреть свои обращения к другим специалистам!\n")
            .toString()
    }

    override fun provideYouAreAuthorizedInfoMessage(specialist: SpecialistDto): String {
        val statusMessage = profileStatusMessageBuilder(specialist)

        return StringBuilder()
            .append("Ваш профиль выглядит вот так: \n")
            .append(getProfileTemplate(specialist))
            .append(statusMessage)
            .append("Вы так же можете просмотреть свои обращения к другим специалистам!\n")
            .toString()
    }

    override fun provideSpecialistRequestInfoMessage(specialists: Collection<SpecialistDto>): String {
        if (specialists.isEmpty()) {
            return StringBuilder()
                .append("На данный момент вы не брали контакты ни у одного специалиста!")
                .toString()
        }

        val specialistsString = specialists.fold(StringBuilder()) { builder, specialist ->
            builder.append(getProfileTemplate(specialist))
        }

        return StringBuilder()
            .append("Ваши заявки:\n")
            .append(specialistsString)
            .append("Вы можете выбрать какую страницу смотреть, для этого нужно просто нажать на кнопку снизу!")
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
            .append("Сначала введите ваше имя.\n")
            .append("Постарайтесь указать полное имя и/или фамилию. Так больше шансов, что заказчики")
            .append("обратят на вас внимание.\n")
            .append("Пример: Максим Иванов")
            .toString()
    }

    override fun provideUserInputProfessionsInfoMessage(availableProfessions: Collection<ProfessionDto>): String {
        return StringBuilder()
            .append("Теперь укажите чем вы занимаетесь.")
            .append("На данный момент наш сервис поддерживает ${availableProfessions.count()} профессий.\n")
            .append("Вы можете выбрать несколько актуальных для вас профессий, после каждого выбора вам будет приходить сообщение")
            .append("c доступными профессиями. После того, как вы выберите актуальные для вас профессии, нажмите кнопку продожить.\n")
            .append("Пожалуйста, не указывайте больше 3-х профессий. Выберете 1-2 основных видов деятельнсоти.\n")
            .append(userInputProfessionTemplate(availableProfessions))
            .toString()
    }

    override fun provideUserInputAnotherProfessionsInfoMessage(availableProfessions: Collection<ProfessionDto>): String {
        return StringBuilder()
            .append("Отлично! Вы можете выбрать ещё одну профессию или продолжить регистрацию.\n")
            .append(userInputProfessionTemplate(availableProfessions))
            .toString()
    }

    override fun provideUserInputNichesInfoMessage(availableNiches: Collection<NicheDto>): String {
        return StringBuilder()
            .append("Теперь укажите в какой нише вы работаете или у вас был опыт работы.\n")
            .append("Вы можете выбрать несколько ниш. После каждого выбора вам будет приходить сообщение")
            .append("c доступными нишами. После того, как вы выберите актуалтые для вас профессии, нажмите продолжить.\n")
            .append("Советуем указывать не более 5 ниш, так как иначе заказчикам будет сложнее вас заметить.\n")
            .append(userInputNicheTemplate(availableNiches))
            .toString()
    }

    override fun provideUserInputAnotherNicheInfoMessage(availableNiches: Collection<NicheDto>): String {
        return StringBuilder()
            .append("Отлично! Вы можете выбрать ещё одну нишу или продолжить регистрацию.\n")
            .append(userInputNicheTemplate(availableNiches))
            .toString()
    }

    override fun provideUserInputKeySkillsInfoMessage(): String {
        return StringBuilder()
            .append("Теперь вы можете указать ваши ключевые навыки или умения.\n")
            .append("Перечислите их через запятую.\n")
            .append(userInputKeySkillsTemplate())
            .toString()
    }

    override fun provideUserInputPortfolioLinkInfoMessage(): String {
        return StringBuilder()
            .append("Теперь нам необходимо ваше портфолио.\n")
            .append("Вы можете указать ссылки на ваши кейсы, прикрепить профиль на различных площадках.\n")
            .append("Можете кратко рассказть про кейсы и опыт работы.\n")
            .toString()
    }

    override fun provideUserInputAboutMeInfoMessage(): String {
        return StringBuilder()
            .append("Сейчас вы можете написать немного о себе.\n")
            .append("Стоит поподробнее описать ваш опыт работы, чем вы гордитесь, подходы в решении задач.\n")
            .append(userInputAboutMeTemplate())
            .toString()
    }

    override fun provideUserInputWorkingConditionsInfoMessage(): String {
        return StringBuilder()
            .append("Теперь нужно указать ваши условия работы.\n")
            .append(userInputWorkingConditionsTemplate())
            .toString()
    }

    override fun provideUserInputEducationGradeInfoMessage(): String {
        return StringBuilder()
            .append("Опишите ваше образование. Расскажите какие куры проходили.\n")
            .append("Стоит указать как можно больше информации.\n")
            .append(userInputEducationGradeTemplate())
            .toString()
    }

    override fun provideUserInputContactLinksInfoMessage(): String {
        return StringBuilder()
            .append("Напишите свои контакты для связи.\n")
            .append(userInputContactLinksTemplate())
            .toString()
    }

    override fun provideHowProfileLooksNowMessage(profile: SpecialistDto): String {
        return StringBuilder()
            .append("На данный момент ваш профиль специалиста выглядит так:\n")
            .append(getProfileTemplate(profile))
            .append("Вы можете продожить или вернуться на главный экран.")
            .toString()
    }

    override fun provideProfilePreviewMessage(profile: SpecialistDto): String {
        return StringBuilder()
            .append("Отлично! Вы почти завершили регистрацию.\n")
            .append("Ваш профиль выглядит так:\n")
            .append("\n")
            .append(getProfileTemplate(profile))
            .append("Когда вы закончите регистрацию, ваш профиль проверят наши специалисты.\n")
            .append("Мы оповестим вас о результате проверки, вам придет сообщение от бота.\n")
            .append("Вы можете в любой момент редактировать информацию о вашем профиле, изменять его видимость для заказчиков и")
            .append("попросить перепроверить в случае если профиль будет откланен.")
            .toString()
    }

    override fun provideUserChangeFullNameInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваше имя. Постарайтесь указать полное имя.\n")
            .append("Пример: Максим Иванов")
            .toString()
    }

    override fun provideUserChangeNicheInfoMessage(niches: Collection<NicheDto>): String {
        return StringBuilder()
            .append("Выберете вашу нишу.\n")
            .append("Вы можете выбрать несколько ниш, после каждого выбора вы сможете выбрать еще или закончить редактирование.")
            .toString()
    }

    override fun provideUserChangeAnotherNicheInfoMessage(niches: Collection<NicheDto>): String {
        return StringBuilder()
            .append("Отлично! Вы можете выбрать еще нишу или закончить редактирование. Советуем выбирать не больше 5 ниш.\n")
            .toString()
    }

    override fun provideUserChangeProfessionsInfoMessage(professions: Collection<ProfessionDto>): String {
        return StringBuilder()
            .append("Выберите вашу профессию.\n")
            .append("Вы можете выбрать несколько профессий, после каждого выбора вы сможете выбрать еще или закончить редактирование.\n")
            .append(userInputProfessionTemplate(professions))
            .toString()
    }

    override fun provideUserChangeAnotherProfessionsInfoMessage(professions: Collection<ProfessionDto>): String {
        return StringBuilder()
            .append("Олично! Вы можете выбрать еще профессию или закончить редактирование. Советуем не выбирать больше 3-x.\n")
            .toString()
    }

    override fun provideUserChangeKeySkillsInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваши ключевые навыки и способности.\n")
            .append(userInputKeySkillsTemplate())
            .toString()
    }

    override fun provideUserChangePortfolioLinkInfoMessage(): String {
        return StringBuilder()
            .append("Укажите ссылку на ваше портфолио, кратко опишите кейсы и опыт работы.\n")
            .toString()
    }

    override fun provideUserChangeAboutMeInfoMessage(): String {
        return StringBuilder()
            .append("Введите информацию о себе. Подробно распишите ваш опыт работы и подходы к решению задач.\n")
            .append(userInputAboutMeTemplate())
            .toString()
    }

    override fun provideUserChangeWorkingConditionsInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваши условия работы.\n")
            .append(userInputWorkingConditionsTemplate())
            .toString()
    }

    override fun provideUserChangeEducationGradeInfoMessage(): String {
        return StringBuilder()
            .append("Введите ваше образование.\n")
            .append(userInputEducationGradeTemplate())
            .toString()
    }

    override fun provideUserChangeContactLinksInfoMessage(): String {
        return StringBuilder()
            .append("Введите вашу контактную информацию.\n")
            .append(userInputContactLinksTemplate())
            .toString()
    }

    private fun profileStatusMessageBuilder(specialist: SpecialistDto): StringBuilder {
        val statusAlias = specialist.profileStatus.alias
        val status = ProfileStatuses.fromAlias(statusAlias)
        val isVisible = specialist.isVisible

        return when (status) {
            NOT_FILLED -> StringBuilder()
                .append("Ваш профиль заполнен не до конца. Если хотите чтобы он был виден на бирже")
                .append("заполните недостоющие поля!\n")

            AWAITING_CONFIRMATION -> StringBuilder()
                .append("Ваш профиль ожидает подтверждения от наших специалистов.\n")
                .append("Они проверят все данные и, если что покажут что и где можно улучшить.\n")
                .append("Обычно проверка за нимает не более суток, но в среднем это пару часов.\n")

            REJECTED -> StringBuilder()
                .append("Наши специалисты проверили вашу заявку и нашли некоторые недочеты.\n")
                .append("Мы описали причину в недавнем сообщении от нашего бота, если вы считаете, что")
                .append("мы не правы или вам не пришло сообщение, то напишите @Danverrr.\n")
                .append("Вы также можете запросить повторную проверку, для этого нажмите на кнопку снизу.\n")

            APPROVED -> StringBuilder()
                .append("Мы проверели ваш профиль и теперь вы можете изменить видимость вашего профиля на бирже.\n")
                .append("На данный момент ваш профиль ")
                .append {
                    return@append if (isVisible) {
                        "виден на площадке.\n"
                    } else {
                        "не виден на площадке.\n"
                    }
                }
                .append("Если вы отредактируете какое-то поле, то ваш профиль будет снова ожидать подтверждения от наших специалистов.\n")
        }
    }

    private fun userInputProfessionTemplate(professions: Collection<ProfessionDto>): StringBuilder {
        return professions.fold(StringBuilder()) { builder, profession ->
            builder.append("${profession.friendlyName}: ")
            builder.append("Нажмите /${profession.alias}\n")
        }
    }

    private fun userInputNicheTemplate(niches: Collection<NicheDto>): StringBuilder {
        return niches.fold(StringBuilder()) { builder, niche ->
            builder.append("${niche.friendlyName}: ")
            builder.append("Нажмите /${niche.alias}\n")
        }
    }

    private fun userInputKeySkillsTemplate(): StringBuilder {
        return StringBuilder()
            .append("Например: Пунктуальность, Упорство, Креатив.\n")
    }

    private fun userInputAboutMeTemplate(): StringBuilder {
        return StringBuilder()
            .append("Это очень важно для ваших будущих заказчиков!\n")
            .append("Наример:\n")
            .append("Я работаю в этой индустрии более трех лет. У меня больше миллиона кейсов, месяц назад я привлек больше 1млн новых подписчиков в инстарграмм,")
            .append(" месяц назад я привлек больше 1млн новых подписчиков в инстарграмм.")
    }

    private fun userInputWorkingConditionsTemplate(): StringBuilder {
        return StringBuilder()
            .append("Стоит указать вашу стоймость за конкретную услугу/услуги и дополнительные условия оплаты труда.\n")
            .append("Например:\n")
            .append("Делаю 10 сторис за 50$. Беру 5% от рекламного бюджета.")
    }

    private fun userInputEducationGradeTemplate(): StringBuilder {
        return StringBuilder()
            .append("Например:\n")
            .append("1. Окончил курс InstaBoss в 2021 году.\n")
            .append("2. Прошел марафон по SMM.")
    }

    private fun userInputContactLinksTemplate(): StringBuilder {
        return StringBuilder()
            .append("Укажите как можно больше ссылок.\n")
            .append("Мы покажем это вашим потенциальным заказчикам.\n")
            .append("Например: TG: ..., VK: ...")
    }

    private fun getProfileTemplate(specialist: SpecialistDto, ignoreContactLinks: Boolean = false): StringBuilder {
        return specialist.run {
            StringBuilder()
                .append("1. Имя:\n ${fullName ?: "-"} \n")
                .append("2. Чем занимается:\n ${professions.joinToString().ifEmpty { "-" }} \n")
                .append("3. Нишы: ${professions.joinToString().ifEmpty { "-" }} \n")
                .append("4. Навыки:\n ${keySkills.joinToString().ifEmpty { "-" }} \n")
                .append("5. Образование:\n ${educationGrade ?: "-"} \n")
                .append("6. Портфолио:\n ${portfolioLink ?: "-"} \n")
                .append("7. Обо мне:\n ${aboutMe ?: "-"} \n")
                .append("8. Условия работы:\n ${workingConditions ?: "-"} \n")
                .apply {
                    if (!ignoreContactLinks) {
                        append("9. Контакты:\n ${contactLinks ?: "-"} \n")
                    }
                }
                .append("\n")
        }
    }
}