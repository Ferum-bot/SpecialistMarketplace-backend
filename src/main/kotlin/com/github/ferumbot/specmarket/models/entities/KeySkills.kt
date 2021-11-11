package com.github.ferumbot.specmarket.models.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "KEY_SKILLS")
data class KeySkills(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    var id: Long?,

    @Column(name = "ALIAS", nullable = false)
    var alias: String,

    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "UPDATED_DATE", nullable = false)
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
