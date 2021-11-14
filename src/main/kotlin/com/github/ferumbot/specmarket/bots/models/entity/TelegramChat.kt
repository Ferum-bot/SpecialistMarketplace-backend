package com.github.ferumbot.specmarket.bots.models.entity

import com.github.ferumbot.specmarket.bots.models.enums.ChatType
import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "telegram_chat")
data class TelegramChat(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "telegram_chat_id", unique = true, nullable = false)
    val telegramChatId: Long,

    @Column(name = "chat_type", nullable = false)
    var chatType: ChatType,

    @Column(name = "title", length = 1000)
    var title: String? = null,

    @Column(name = "description", length = 5000)
    var description: String? = null,

    @Column(name = "invite_link", length = 1000)
    var inviteLink: String? = null,

    @Column(name = "created_date", updatable = false, nullable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_date", nullable = false)
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
