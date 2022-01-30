package com.github.ferumbot.specmarket.models.entities.specialist.enum

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
    }
}