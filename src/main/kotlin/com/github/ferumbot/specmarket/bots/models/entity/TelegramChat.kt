package com.github.ferumbot.specmarket.bots.models.entity

import com.github.ferumbot.specmarket.bots.models.enums.ChatType
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class TelegramChat(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "TELEGRAM_CHAT_ID", unique = true, nullable = false)
    val telegramChatId: Long? = null,

    @Column(name = "CHAT_TYPE")
    var chatType: ChatType,

    @Column(name = "TITLE")
    var title: String? = null,

    @Column(name = "DESCRIPTION")
    var description: String? = null,

    @Column(name = "INVITE_LINK")
    var inviteLink: String? = null,

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
