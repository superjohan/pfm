package com.aerodeko.pfm.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.util.*

/**
 * Created by rm on 10/12/2016.
 */

class EventManager(context: Context) {
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

    fun getEvents(date: Date): List<Event> {
        val startDate = Calendar.getInstance(TimeZone.getDefault())
        startDate.time = date
        startDate.set(Calendar.HOUR_OF_DAY, 0)
        startDate.set(Calendar.MINUTE, 0)
        startDate.set(Calendar.SECOND, 0)
        startDate.set(Calendar.MILLISECOND, 0)

        val endDate = startDate.clone() as Calendar
        endDate.add(Calendar.DAY_OF_MONTH, 1)

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
