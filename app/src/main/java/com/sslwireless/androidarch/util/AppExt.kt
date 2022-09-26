package com.sslwireless.androidarch.util

import com.google.gson.GsonBuilder
import kotlin.reflect.KClass

fun <T> T.makeJson(isPretty: Boolean = false): String {
    return if (isPretty)
        GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(this)
    else
        GsonBuilder().serializeNulls().create().toJson(this)
}

inline fun <reified T : Any> String.makeObject(): T {
    return GsonBuilder().serializeNulls().create().fromJson(
        this,
        T::class.java
    )
}
