package com.aerodeko.pfm.model

import android.content.Context
import android.util.Log

/**
 * Created by rm on 10/12/2016.
 */

class BudgetInteractor(context: Context, val listener: Listener) {
    private val TAG = "BudgetInteractor"

    val budgetDatabaseManager: BudgetDatabaseManager

    init {
        budgetDatabaseManager = BudgetDatabaseManager(context)
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
