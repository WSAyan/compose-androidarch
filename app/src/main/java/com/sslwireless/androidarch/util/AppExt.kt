package com.sslwireless.androidarch.util

import com.google.gson.GsonBuilder
import kotlin.reflect.KClass

fun <T> T.makeJson(isPretty: Boolean = false): String {
    return if (isPretty)
        GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(this)
    else
        GsonBuilder().serializeNulls().create().toJson(this)
}

fun <T : Any> String.makeObject(classType: KClass<T>): Any {
    return GsonBuilder().serializeNulls().create().fromJson(
        this,
        classType.java
    )
}
