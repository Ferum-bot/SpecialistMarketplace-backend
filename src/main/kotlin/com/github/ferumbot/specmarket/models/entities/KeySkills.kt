package com.github.ferumbot.specmarket.models.entities

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class KeySkills(

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    var id: Long?,

    @Column(name = "ALIAS", nullable = false)
    var alias: String,

    @Column(name = "CREATED_DATE", nullable = false, updatable = false)
    var createdDate: LocalDateTime = LocalDateTime.now(),

    @Column(name = "UPDATED_DATE", nullable = false)
    var updatedDate: LocalDateTime = LocalDateTime.now(),
) {

}
