package com.aerodeko.pfm.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.aerodeko.pfm.extensions.calendarFromDays
import com.aerodeko.pfm.extensions.timeInDays
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

    fun addEvent(value: Double, date: Calendar = Calendar.getInstance(), description: String? = null): Event? {
        val values = ContentValues()
        values.put(EventDatabaseHelper.Event.Columns.DATE, date.timeInDays)
        values.put(EventDatabaseHelper.Event.Columns.VALUE, value)
        values.put(EventDatabaseHelper.Event.Columns.DESCRIPTION, description)

        val rowId = database.insert(EventDatabaseHelper.Event.Table.NAME, null, values)

        if (rowId != -1L) {
            return Event(date, value, description)
        } else {
            return null
        }
    }

    fun getEvents(date: Calendar): List<Event> {
        val cursor = database.query(
                EventDatabaseHelper.Event.Table.NAME,
                null, // all columns
                EventDatabaseHelper.Event.Columns.DATE + "=" + date.timeInDays,
                null,
                null,
                null,
                null
        )

        val events = mutableListOf<Event>()

        if (cursor.moveToFirst()) {
            while (! cursor.isAfterLast) {
                val days = cursor.getLong(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.DATE))
                val value = cursor.getDouble(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.VALUE))
                val description = cursor.getString(cursor.getColumnIndex(EventDatabaseHelper.Event.Columns.DESCRIPTION))
                val event = Event(
                        date = calendarFromDays(days),
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
