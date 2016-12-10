package com.aerodeko.pfm.model

import android.content.Context

/**
 * Created by rm on 10/12/2016.
 */

class EventInteractor(context: Context) {
    val eventManager: EventManager

    init {
        eventManager = EventManager(context)
    }
}