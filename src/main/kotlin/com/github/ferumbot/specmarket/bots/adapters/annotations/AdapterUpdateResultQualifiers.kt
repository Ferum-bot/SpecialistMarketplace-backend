package com.github.ferumbot.specmarket.bots.adapters.annotations

import org.springframework.beans.factory.annotation.Qualifier
import kotlin.annotation.AnnotationTarget.*


@Target(FIELD, TYPE_PARAMETER, VALUE_PARAMETER, CLASS, FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Qualifier
annotation class GeneralAdapterQualifier