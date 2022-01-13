package com.github.ferumbot.specmarket.core.extensions

inline fun <reified T> T?.ifNull(action: () -> Unit) {
    if (this == null) {
        action.invoke()
    }
}