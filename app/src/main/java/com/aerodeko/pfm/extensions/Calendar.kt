package com.aerodeko.pfm.extensions

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by rm on 11/12/2016.
 */

var Calendar.timeInDays: Long
    get() = TimeUnit.MILLISECONDS.toDays(this.timeInMillis)

    set(value) {
        this.timeInMillis = TimeUnit.DAYS.toMillis(value)
    }

fun calendarFromDays(days: Long): Calendar {
    val date = Calendar.getInstance()
    date.timeInDays = days

    return date
}
