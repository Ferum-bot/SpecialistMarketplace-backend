package com.github.ferumbot.specmarket.models.entities

import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "specialist")
data class Specialist(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "full_name", length = 1000)
    var fullName: String? = null,

    @Column(name = "department", length = 1000)
    var department: String? = null,

    @ManyToMany(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
        fetch = FetchType.LAZY,
    )
    @JoinTable(
        name = "specialists_to_professions",
        joinColumns = [JoinColumn(name = "specialist_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "profession_id", referencedColumnName = "id")]
    )
    var professions: MutableCollection<Profession> = mutableListOf(),

    @ManyToMany(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
        fetch = FetchType.LAZY
    )
    @JoinTable(
        name = "specialists_to_niches",
        joinColumns = [JoinColumn(name = "specialist_id", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "niche_id", referencedColumnName = "id")]
    )
    var niches: MutableCollection<Niche> = mutableListOf(),

    @OneToMany(
        cascade = [CascadeType.ALL], orphanRemoval = true,
        fetch = FetchType.LAZY,
    )
    var keySkills: MutableCollection<KeySkills> = mutableListOf(),

    @Column(name = "portfolio_link", length = 1000)
    var portfolioLink: String? = null,

    @Column(name = "about_me", length = 5000)
    var aboutMe: String? = null,

    @Column(name = "working_conditions", length = 1000)
    var workingConditions: String? = null,

    @Column(name = "education_grade", length = 1000)
    var educationGrade: String? = null,

    @Column(name = "contact_links", length = 1000)
    var contactLinks: String? = null,

    @Column(name = "is_completely_filled", nullable = false)
    var isCompletelyFilled: Boolean = false,

    @Column(name = "is_visible", nullable = false)
    var isVisible: Boolean = false,

    @OneToOne(
        cascade = [CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE],
        fetch = FetchType.LAZY,
    )
    @JoinColumn(name = "telegram_user_id", referencedColumnName = "id")
    var telegramUser: TelegramUser? = null,

    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_date", nullable = false)
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
