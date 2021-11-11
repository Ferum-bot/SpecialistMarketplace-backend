package com.github.ferumbot.specmarket.bots.models.entity

import com.github.ferumbot.specmarket.bots.models.enums.ChatType
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "TELEGRAM_CHAT")
data class TelegramChat(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "TELEGRAM_CHAT_ID", unique = true, nullable = false)
    val telegramChatId: Long,

    @Column(name = "CHAT_TYPE", nullable = false)
    var chatType: ChatType,

    @Column(name = "TITLE", length = 1000)
    var title: String? = null,

    @Column(name = "DESCRIPTION", length = 5000)
    var description: String? = null,

    @Column(name = "INVITE_LINK", length = 1000)
    var inviteLink: String? = null,

    @Column(name = "CREATED_DATE", updatable = false, nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "UPDATED_DATE", nullable = false)
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
