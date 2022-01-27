package com.github.ferumbot.specmarket.models.entities.specialist.enum

enum class ProfileStatuses(val alias: String) {
    NOT_FILLED("not_filled"),
    AWAITING_CONFIRMATION("awaiting_confirmation"),
    REJECTED("rejected"),
    APPROVED("approved");
}