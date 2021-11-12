package com.github.ferumbot.specmarket.bots.models.entity

import com.github.ferumbot.specmarket.bots.models.entity.embeded.UserBotState
import com.github.ferumbot.specmarket.bots.state_machine.state.UnRegisteredState
import com.github.ferumbot.specmarket.models.entities.Specialist
import org.apache.catalina.User
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "telegram_user")
data class TelegramUser(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "telegram_user_id", unique = true, nullable = false)
    var telegramUserId: Long,

    @Column(name = "personal_telegram_chat_id", unique = true, nullable = false)
    var personalTelegramChatId: Long,

    @Column(name = "first_name", length = 1000)
    var firstName: String? = null,

    @Column(name = "last_name", length = 1000)
    var lastName: String? = null,

    @Column(name = "user_name", length = 1000)
    var userName: String? = null,

    @Embedded
    var currentBotState: UserBotState = UserBotState(),

    @Column(name = "is_bot")
    var isBot: Boolean,

    @Column(name = "language_code")
    var languageCode: String? = null,

    @OneToOne(mappedBy = "telegramUser")
    var specialist: Specialist? = null,

    @ManyToMany(
        cascade = [CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE],
        fetch = FetchType.LAZY,
        targetEntity = Specialist::class,
    )
    @JoinTable(
        name = "telegram_user_to_specialist_requests",
        joinColumns = [ JoinColumn(name = "telegram_user_id", referencedColumnName = "id") ],
        inverseJoinColumns = [ JoinColumn(name = "specialist_id", referencedColumnName = "id") ]
    )
    var specialistsRequests: Collection<Specialist> = listOf(),

    @Column(name = "created_date", updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_date")
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
