package com.github.ferumbot.specmarket.models.entities.specialist

import com.github.ferumbot.specmarket.bots.models.entity.TelegramUser
import com.github.ferumbot.specmarket.models.entities.specifications.KeySkills
import com.github.ferumbot.specmarket.models.entities.specifications.Niche
import com.github.ferumbot.specmarket.models.entities.specifications.Profession
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "specialist_profile")
data class SpecialistProfile(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "full_name", length = 1000)
    var fullName: String? = null,

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

    @Column(name = "is_visible", nullable = false)
    var isVisible: Boolean = false,

    @ManyToOne(
        cascade = [CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH],
        fetch = FetchType.EAGER,
    )
    @JoinColumn(name = "status_id", nullable = false)
    var status: SpecialistProfileStatus = SpecialistProfileStatus(),

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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpecialistProfile

        if (id != other.id) return false
        if (fullName != other.fullName) return false
        if (portfolioLink != other.portfolioLink) return false
        if (aboutMe != other.aboutMe) return false
        if (workingConditions != other.workingConditions) return false
        if (educationGrade != other.educationGrade) return false
        if (contactLinks != other.contactLinks) return false
        if (isVisible != other.isVisible) return false
        if (status != other.status) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (fullName?.hashCode() ?: 0)
        result = 31 * result + (portfolioLink?.hashCode() ?: 0)
        result = 31 * result + (aboutMe?.hashCode() ?: 0)
        result = 31 * result + (workingConditions?.hashCode() ?: 0)
        result = 31 * result + (educationGrade?.hashCode() ?: 0)
        result = 31 * result + (contactLinks?.hashCode() ?: 0)
        result = 31 * result + isVisible.hashCode()
        result = 31 * result + status.hashCode()
        return result
    }

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
