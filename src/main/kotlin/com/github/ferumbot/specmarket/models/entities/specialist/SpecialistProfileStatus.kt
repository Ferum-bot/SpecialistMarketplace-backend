package com.github.ferumbot.specmarket.models.entities.specialist

import com.github.ferumbot.specmarket.models.entities.specialist.converters.SpecialistProfileStatusNameConverter
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses.NOT_FILLED
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "specialist_profile_status")
data class SpecialistProfileStatus(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "alias", nullable = false, unique = true)
    @Convert(converter = SpecialistProfileStatusNameConverter::class)
    var alias: ProfileStatuses = NOT_FILLED,

    @Column(name = "name", nullable = false, length = 1000)
    var name: String = "",

    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SpecialistProfileStatus

        if (id != other.id) return false
        if (alias != other.alias) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + alias.hashCode()
        result = 31 * result + name.hashCode()
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
