package com.github.ferumbot.specmarket.models.entities

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
