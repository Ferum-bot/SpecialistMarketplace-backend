package com.github.ferumbot.specmarket.models.entities.specifications

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "filter")
data class Filter(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "alias", unique = true, nullable = false)
    var alias: String,

    @Column(name = "name", nullable = false)
    var name: String,

    @Column(name = "created_date", updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_date")
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Filter

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
