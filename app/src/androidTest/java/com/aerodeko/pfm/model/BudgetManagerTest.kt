package com.aerodeko.pfm.model

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by rm on 09/05/2017.
 */
@RunWith(AndroidJUnit4::class)
class BudgetManagerTest {
    private lateinit var budgetDatabaseManager: BudgetDatabaseManager
    private lateinit var budgetManager: BudgetManager

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        this.budgetDatabaseManager = BudgetDatabaseManager(context, TimeZone.getTimeZone("GMT"))
        this.budgetDatabaseManager.clearTables()

        this.budgetManager = BudgetManager(this.budgetDatabaseManager)
    }

    @Test
    fun test_current_budget_getter_works() {
        val now = Date()
        val addedBudget = this.budgetManager.addBudget(
            name = "budget",
            amount = 100.0,
            startDate = Date(now.time - 1000),
            endDate = Date(now.time + 1000)
        )
        val currentBudget = this.budgetManager.currentBudget

        assertEquals(addedBudget, currentBudget)
    }

    @Test
    fun test_daily_budget_is_correct_with_no_events() {

    }
}
