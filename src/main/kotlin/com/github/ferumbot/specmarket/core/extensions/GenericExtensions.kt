package com.github.ferumbot.specmarket.core.extensions

inline fun <reified T> T?.ifNull(action: () -> Unit) {
    if (this == null) {
        action.invoke()
    }
}

inline fun <reified T, reified P> T.transform(transform: (T) -> P): P {
    return transform(this)
}

inline fun <reified T> T?.falseIfNull(): Boolean {
    return this != null
}

inline fun <reified T> T?.trueIfNull(): Boolean {
    return this == null
}