package com.aerodeko.pfm.model

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by rm on 09/05/2017.
 */

class BudgetManager(private val budgetDatabaseManager: BudgetDatabaseManager) {
    private var budgets: List<Budget> = budgetDatabaseManager.getBudgets()

    // FIXME: assumes no overlapping budgets, which we *do* want to support
    val currentBudget: Budget?
        get() {
            val now = Date()

            budgets.forEach { budget ->
                if (budget.startDate.time <= now.time && budget.endDate.time >= now.time) {
                    return budget
                }
            }

            return null
        }

    fun addBudget(name: String, amount: Double, startDate: Date, endDate: Date): Budget? {
        val budget = budgetDatabaseManager.addBudget(name, amount, startDate, endDate)

        budgets = budgetDatabaseManager.getBudgets()

        return budget
    }
}
