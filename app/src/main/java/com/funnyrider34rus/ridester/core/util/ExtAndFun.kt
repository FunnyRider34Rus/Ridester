package com.funnyrider34rus.ridester.core.util

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun timestampToDate(timestamp: Timestamp?): String {
    val long = (timestamp?.seconds?.times(1000) ?: 0) + (timestamp?.nanoseconds?.div(1000000) ?: 0)
    val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
    return simpleDateFormat.format(long)
}

fun addOrRemoveIfContains(source: List<String>?, value: String): List<String>? {
    var modified = source?.toMutableList()
    if (source != null) {
        if (source.contains(value)) {
            modified?.remove(value)
        } else {
            modified?.add(value)
        }
    } else {
        modified = mutableListOf(value)
    }
    return modified
}