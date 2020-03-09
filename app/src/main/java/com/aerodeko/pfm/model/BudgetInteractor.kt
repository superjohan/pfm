package com.aerodeko.pfm.model

import android.content.Context
import android.util.Log

/**
 * Created by rm on 10/12/2016.
 */

private const val TAG = "BudgetInteractor"

class BudgetInteractor(context: Context, val listener: Listener) {

    val budgetDatabaseManager: BudgetDatabaseManager = BudgetDatabaseManager(context)

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
