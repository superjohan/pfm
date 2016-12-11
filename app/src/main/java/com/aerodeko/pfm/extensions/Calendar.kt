package com.aerodeko.pfm.extensions

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by rm on 11/12/2016.
 */

val Calendar.timeInDays: Long
    get() = TimeUnit.MILLISECONDS.toDays(this.timeInMillis)
