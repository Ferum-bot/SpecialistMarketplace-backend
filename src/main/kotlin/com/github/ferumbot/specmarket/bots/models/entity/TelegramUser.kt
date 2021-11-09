package com.github.ferumbot.specmarket.bots.models.entity

import com.github.ferumbot.specmarket.bots.state_machine.state.BotState
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnRegisteredState
import com.github.ferumbot.specmarket.models.entities.Specialist
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class TelegramUser(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "TELEGRAM_USER_ID", unique = true, nullable = false)
    var telegramUserId: Long,

    @Column(name = "PERSONAL_TELEGRAM_CHAT_ID", unique = true, nullable = false)
    var personalTelegramChatId: Long,

    @Column(name = "CURRENT_BOT_STATE")
    var currentBotState: String = UnRegisteredState.screenName,

    @Column(name = "FIRST_NAME", length = 1000)
    var firstName: String? = null,

    @Column(name = "LAST_NAME", length = 1000)
    var lastName: String? = null,

    @Column(name = "USER_NAME", length = 1000)
    var userName: String? = null,

    @Column(name = "IS_BOT")
    var isBot: Boolean,

    @Column(name = "LANGUAGE_CODE")
    var languageCode: String? = null,

    @OneToOne(mappedBy = "telegramUser")
    var specialist: Specialist? = null,

    @Column(name = "CREATED_DATE", updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "UPDATED_DATE")
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {

    @PrePersist
    fun onCreate() {
        createdDate = LocalDateTime.now()
        updatedDate = LocalDateTime.now()
    }

    @PreUpdate
    fun onUpdate() {
        updatedDate = LocalDateTime.now()
    }
}
