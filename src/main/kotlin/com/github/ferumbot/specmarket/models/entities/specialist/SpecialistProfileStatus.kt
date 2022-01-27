package com.github.ferumbot.specmarket.models.entities.specialist

import com.github.ferumbot.specmarket.models.entities.specialist.converters.SpecialistProfileStatusNameConverter
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses
import com.github.ferumbot.specmarket.models.entities.specialist.enum.ProfileStatuses.NOT_FILLED
import javax.persistence.*

@Entity
@Table(name = "specialist_profile_status")
data class SpecialistProfileStatus(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "name", nullable = false, unique = true)
    @Convert(converter = SpecialistProfileStatusNameConverter::class)
    var name: ProfileStatuses = NOT_FILLED,
)
