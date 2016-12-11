package com.aerodeko.pfm.model

import com.aerodeko.pfm.extensions.timeInDays
import java.util.*

/**
 * Created by rm on 10/12/2016.
 */

data class Event(val date: Calendar, val value: Double, var description: String?) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Event

        if (date.timeInDays != other.date.timeInDays) return false
        if (value != other.value) return false
        if (description != other.description) return false

        return true
    }

    override fun hashCode(): Int {
        var result = date.timeInDays.hashCode()
        result = 31 * result + value.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        return result
    }
}
