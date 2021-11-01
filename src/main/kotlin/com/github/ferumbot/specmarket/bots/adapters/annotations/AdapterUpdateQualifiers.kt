package com.github.ferumbot.specmarket.bots.adapters.annotations

import org.springframework.beans.factory.annotation.Qualifier
import java.lang.annotation.ElementType
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*

@Target(FIELD, TYPE_PARAMETER, VALUE_PARAMETER, CLASS, FUNCTION)
@Retention(RUNTIME)
@Qualifier
annotation class CommonAdapterQualifier

@Target(FIELD, TYPE_PARAMETER, VALUE_PARAMETER, CLASS, FUNCTION)
@Retention(RUNTIME)
@Qualifier
annotation class IAmCustomerAdapterQualifier

@Target(FIELD, TYPE_PARAMETER, VALUE_PARAMETER, CLASS, FUNCTION)
@Retention(RUNTIME)
@Qualifier
annotation class IAmSpecialistAdapterQualifier

@Target(FIELD, TYPE_PARAMETER, VALUE_PARAMETER, CLASS, FUNCTION)
@Retention(RUNTIME)
@Qualifier
annotation class AllSpecialistsAdapterQualifier
