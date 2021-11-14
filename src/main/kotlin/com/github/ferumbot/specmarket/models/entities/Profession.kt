package com.github.ferumbot.specmarket.models.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "profession")
data class Profession(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "friendly_name", nullable = false, length = 1000)
    var friendlyName: String,

    @Column(name = "alias", nullable = false, length = 1000, unique = true)
    var alias: String,

    @Column(name = "short_description", nullable = false, length = 500)
    var shortDescription: String,

    @Column(name = "long_description", length = 5000)
    var longDescription: String? = null,

    @Column(name = "created_date", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Profession

        if (id != other.id) return false
        if (friendlyName != other.friendlyName) return false
        if (alias != other.alias) return false
        if (shortDescription != other.shortDescription) return false
        if (longDescription != other.longDescription) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + friendlyName.hashCode()
        result = 31 * result + alias.hashCode()
        result = 31 * result + shortDescription.hashCode()
        result = 31 * result + (longDescription?.hashCode() ?: 0)
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
