package com.aerodeko.pfm.model

import android.content.Context
import android.util.Log

/**
 * Created by rm on 10/12/2016.
 */

class EventInteractor(context: Context, val listener: Listener) {
    private val TAG = "EventInteractor"

    val eventManager: EventManager

    init {
        eventManager = EventManager(context)
    }

    fun addIncome() {
        Log.d(TAG, "addIncome: TODO")

        listener.onOpenAddIncome()
    }

    fun addExpense() {
        Log.d(TAG, "addExpense: TODO")

        listener.onOpenAddExpense()
    }

    interface Listener {
        fun onOpenAddIncome()

        fun onOpenAddExpense()
    }
}
