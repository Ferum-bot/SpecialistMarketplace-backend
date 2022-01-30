package com.github.ferumbot.specmarket.models.entities.specifications

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "key_skills")
data class KeySkills(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "alias", nullable = false)
    var value: String,

    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as KeySkills

        if (id != other.id) return false
        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + value.hashCode()
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
