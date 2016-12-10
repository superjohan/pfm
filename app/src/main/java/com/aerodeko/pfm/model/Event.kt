package com.aerodeko.pfm.model

import java.util.*

/**
 * Created by rm on 10/12/2016.
 */

data class Event(val date: Date, val value: Double, var description: String) {
    val dateAsSeconds: Long
        get() = date.time / 1000
}
