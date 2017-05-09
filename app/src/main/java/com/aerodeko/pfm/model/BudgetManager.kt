package com.aerodeko.pfm.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.aerodeko.pfm.extensions.setTimeToStartOfDate
import java.util.*

/**
 * Created by rm on 10/12/2016.
 */

class BudgetManager(context: Context, val timeZone: TimeZone = TimeZone.getDefault()) {
    private val database: SQLiteDatabase

    init {
        val databaseHelper = EventDatabaseHelper(context)
        database = databaseHelper.writableDatabase
    }

    internal fun clearTables() {
        database.delete(EventDatabaseHelper.Budget.Table.NAME, null, null)
        database.delete(EventDatabaseHelper.Event.Table.NAME, null, null)
    }

    fun addBudget(name: String, amount: Double, startDate: Date, endDate: Date): Budget? {
        val values = ContentValues()
        values.put(EventDatabaseHelper.Budget.Columns.NAME, name)
        values.put(EventDatabaseHelper.Budget.Columns.AMOUNT, amount)
        values.put(EventDatabaseHelper.Budget.Columns.START_DATE, startDate.time)
        values.put(EventDatabaseHelper.Budget.Columns.END_DATE, endDate.time)

        val rowId = database.insert(EventDatabaseHelper.Budget.Table.NAME, null, values)

        if (rowId != -1L) {
            return Budget(name, amount, startDate, endDate)
        } else {
            return null
        }
    }

    fun getBudgets(): List<Budget> {
        val cursor = database.query(
                EventDatabaseHelper.Budget.Table.NAME,
                null,
                null,
                null,
                null,
                null,
                null
        )

        val budgets = mutableListOf<Budget>()

        if (cursor.moveToFirst()) {
            while (! cursor.isAfterLast) {
                val name = cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.Budget.Columns.NAME))
                val amount = cursor.getDouble(cursor.getColumnIndex(EventDatabaseHelper.Budget.Columns.AMOUNT))
                val startDate = cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.Budget.Columns.START_DATE))
                val endDate = cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.Budget.Columns.END_DATE))
                val budget = Budget(
                        name = name,
                        amount = amount,
                        startDate = Date(startDate),
                        endDate = Date(endDate)
                )

                budgets.add(budget)

                cursor.moveToNext()
            }
        }

        cursor.close()

        return budgets
    }

    fun addEvent(value: Double, date: Date = Date(), description: String? = null): Event? {
        val values = ContentValues()
        values.put(EventDatabaseHelper.Event.Columns.DATE, date.time)
        values.put(EventDatabaseHelper.Event.Columns.VALUE, value)
        values.put(EventDatabaseHelper.Event.Columns.DESCRIPTION, description)

        val rowId = database.insert(EventDatabaseHelper.Event.Table.NAME, null, values)

        if (rowId != -1L) {
            return Event(date, value, description)
        } else {
            return null
        }
    }

    fun getEvents(budget: Budget): List<Event> {
        val startDate = Calendar.getInstance(timeZone)
        startDate.setTimeToStartOfDate(budget.startDate)

        val endDate = Calendar.getInstance(timeZone)
        endDate.setTimeToStartOfDate(budget.endDate)

        val cursor = database.query(
                EventDatabaseHelper.Event.Table.NAME,
                null,
                EventDatabaseHelper.Event.Columns.DATE + " between " + startDate.timeInMillis + " and " + endDate.timeInMillis,
                null,
                null,
                null,
                null
        )

        val events = mutableListOf<Event>()

        if (cursor.moveToFirst()) {
            do {
                val date = cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.DATE))
                val value = cursor.getDouble(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.VALUE))
                val description = cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.DESCRIPTION))
                val event = Event(
                        date = Date(date),
                        value = value,
                        description = description
                )

                events.add(event)
            } while (cursor.moveToNext())
        }

        cursor.close()

        return events
    }
}
