package com.aerodeko.pfm.model

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.*

/**
 * Created by rm on 11/12/2016.
 */
@RunWith(AndroidJUnit4::class)
class BudgetDatabaseManagerTest {
    private lateinit var budgetDatabaseManager: BudgetDatabaseManager

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext

        this.budgetDatabaseManager = BudgetDatabaseManager(context, TimeZone.getTimeZone("GMT"))
        this.budgetDatabaseManager.clearTables()
    }

    @Test
    fun test_adding_event_creates_valid_event() {
        val expectedValue = 1.0
        // this bit with the dates sucks. Do Better
        val date = Date(1234567890)
        val date2 = Date(2000000000)
        val budget = Budget("budget", 0.0, date, date2)

        val event = this.budgetDatabaseManager.addEvent(expectedValue, date)
        val events = this.budgetDatabaseManager.getEvents(budget)

        assertEquals("only one event should be received", 1, events.size)

        val eventFromDatabase = events[0]

        assertEquals("events should be equal", event, eventFromDatabase)
    }

    @Test
    fun test_getting_event_for_a_certain_date_only_gets_events_for_that_date() {
        val date1 = Calendar.getInstance()
        val date2 = date1.clone() as Calendar
        date2.add(Calendar.DAY_OF_MONTH, 1)
        val date3 = date2.clone() as Calendar
        date3.add(Calendar.DAY_OF_MONTH, 1)

        val event1date1 = this.budgetDatabaseManager.addEvent(1.0, date1.time)
        val event1date2 = this.budgetDatabaseManager.addEvent(3.0, date1.time)
        val event2 = this.budgetDatabaseManager.addEvent(2.0, date2.time)

        val budget1 = Budget("budget 1", 0.0, date1.time, date2.time)
        val budget2 = Budget("budget 2", 0.0, date2.time, date3.time)

        val events1 = this.budgetDatabaseManager.getEvents(budget1)
        val events2 = this.budgetDatabaseManager.getEvents(budget2)

        assertEquals("only two events for date", 2, events1.size)
        assertEquals("only one event for date", 1, events2.size)
        assertEquals("events for the date should be equal", event1date1, events1[0])
        assertEquals("events for the date should be equal", event1date2, events1[1])
        assertEquals("events for the date should be equal", event2, events2[0])
    }

    @Test
    fun test_adding_budget_creates_valid_budget() {
        val budget = this.budgetDatabaseManager.addBudget(
            name = "budget",
            amount = 10.0,
            startDate = Date(1234567890),
            endDate = Date(2345678901)
        )

        assertNotNull("budget should not be null", budget)

        val budgets = this.budgetDatabaseManager.getBudgets()

        assertEquals("should have one budget", 1, budgets.size)

        val budgetFromDatabase = budgets[0]

        assertEquals("budgets should be equal", budget, budgetFromDatabase)
    }
}
