package com.aerodeko.pfm.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.aerodeko.pfm.extensions.setTimeToStartOfDate
import java.util.*

/**
 * Created by rm on 10/12/2016.
 */

class EventManager(context: Context, val timeZone: TimeZone = TimeZone.getDefault()) {
    private val database: SQLiteDatabase

    init {
        val databaseHelper = EventDatabaseHelper(context)
        database = databaseHelper.writableDatabase
    }

    internal fun clearEvents() {
        database.delete(EventDatabaseHelper.Event.Table.NAME, null, null)
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
            while (! cursor.isAfterLast) {
                val date = cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.DATE))
                val value = cursor.getDouble(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.VALUE))
                val description = cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.DESCRIPTION))
                val event = Event(
                        date = Date(date),
                        value = value,
                        description = description
                )

                events.add(event)

                cursor.moveToNext()
            }
        }

        cursor.close()

        return events
    }
}
