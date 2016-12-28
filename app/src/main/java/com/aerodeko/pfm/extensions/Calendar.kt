package com.aerodeko.pfm.extensions

import java.util.*

/**
 * Created by rm on 28/12/2016.
 */

fun Calendar.setTimeToStartOfDate(date: Date) {
    this.time = date
    this.set(Calendar.HOUR_OF_DAY, 0)
    this.set(Calendar.MINUTE, 0)
    this.set(Calendar.SECOND, 0)
    this.set(Calendar.MILLISECOND, 0)
}
