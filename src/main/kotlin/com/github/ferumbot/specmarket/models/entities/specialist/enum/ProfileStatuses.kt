package com.github.ferumbot.specmarket.models.entities.specialist.enum

import com.github.ferumbot.specmarket.core.extensions.falseIfNull

enum class ProfileStatuses(val alias: String) {
    NOT_FILLED("not_filled"),
    AWAITING_CONFIRMATION("awaiting_confirmation"),
    REJECTED("rejected"),
    APPROVED("approved");

    companion object {

        fun fromAlias(alias: String): ProfileStatuses {
            return when(alias) {
                NOT_FILLED.alias -> NOT_FILLED
                AWAITING_CONFIRMATION.alias -> AWAITING_CONFIRMATION
                REJECTED.alias -> REJECTED
                APPROVED.alias -> APPROVED
                else -> NOT_FILLED
            }
        }

        fun isPresented(alias: String): Boolean {
            return ProfileStatuses.values()
                .find { it.alias == alias }
                .falseIfNull()
        }
    }
}