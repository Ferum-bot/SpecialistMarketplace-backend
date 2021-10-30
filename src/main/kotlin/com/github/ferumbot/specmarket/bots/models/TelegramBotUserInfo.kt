package com.github.ferumbot.specmarket.bots.models

import com.github.ferumbot.specmarket.bots.state_machine.state.StartScreenState
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class TelegramBotUserInfo(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long? = null,

    @Column(name = "CHAT_ID", unique = true, nullable = false)
    var chatId: Long? = null,

    @Column(name = "USER_ID", unique = true, nullable = false)
    val userId: Long? = null,

    @Column(name = "CURRENT_STATE", length = 1000)
    var currentState: String = StartScreenState.screenName,

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
