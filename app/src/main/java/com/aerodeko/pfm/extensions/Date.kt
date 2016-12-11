package com.aerodeko.pfm.extensions

import java.util.*

/**
 * Created by rm on 11/12/2016.
 */

val Date.dateAsSeconds: Long
    get() = this.time / 1000
