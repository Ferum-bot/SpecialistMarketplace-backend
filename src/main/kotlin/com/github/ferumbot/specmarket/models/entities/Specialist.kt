package com.github.ferumbot.specmarket.models.entities

import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Specialist(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    var id: Long?,

    @Column(name = "FULL_NAME", nullable = false, length = 1000)
    var fullName: String,

    @Column(name = "DEPARTMENT", nullable = false, length = 1000)
    var department: String,

    @Column(name = "ACTIVITY", nullable = false, length = 1000)
    var activity: String,

    @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true)
    var keySkills: Collection<KeySkills> = listOf(),

    @Column(name = "PORTFOLIO_LINK", nullable = false, length = 1000)
    var portfolioLink: String,

    @Column(name = "ABOUT_ME", nullable = false, length = 5000)
    var aboutMe: String,

    @Column(name = "WORKING_CONDITIONS", nullable = false, length = 1000)
    var workingConditions: String,

    @Column(name = "EDUCATION_GRADE", nullable = false, length = 1000)
    var educationGrade: String,

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
