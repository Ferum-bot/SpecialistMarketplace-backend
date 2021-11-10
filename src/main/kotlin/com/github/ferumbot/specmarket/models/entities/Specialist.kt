package com.github.ferumbot.specmarket.models.entities

import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Specialist(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    var id: Long? = null,

    @Column(name = "FULL_NAME", length = 1000)
    var fullName: String? = null,

    @Column(name = "DEPARTMENT", length = 1000)
    var department: String? = null,

    @Column(name = "ACTIVITY", length = 1000)
    var activity: String? = null,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var keySkills: Collection<KeySkills> = listOf(),

    @Column(name = "PORTFOLIO_LINK", length = 1000)
    var portfolioLink: String? = null,

    @Column(name = "ABOUT_ME", length = 5000)
    var aboutMe: String? = null,

    @Column(name = "WORKING_CONDITIONS", length = 1000)
    var workingConditions: String? = null,

    @Column(name = "EDUCATION_GRADE", length = 1000)
    var educationGrade: String? = null,

    @Column(name = "CONTACT_LINKS", length = 1000)
    var contactLinks: String? = null,

    @Column(name = "IS_COMPLETELY_FILLED", nullable = false)
    var isCompletelyFilled: Boolean = false,

    @Column(name = "IS_VISIBLE", nullable = false)
    var isVisible: Boolean = false,

    @OneToOne(cascade = [CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE])
    @JoinColumn(name = "TELEGRAM_USER_ID", referencedColumnName = "ID")
    var telegramUser: TelegramUser? = null,

    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "UPDATED_DATE", nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now()
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
