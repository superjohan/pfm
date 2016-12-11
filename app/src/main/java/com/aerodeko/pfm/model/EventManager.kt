package com.aerodeko.pfm.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.aerodeko.pfm.extensions.dateAsSeconds
import java.util.*

/**
 * Created by rm on 10/12/2016.
 */

class EventManager(context: Context) {
    val database: SQLiteDatabase

    init {
        val databaseHelper = EventDatabaseHelper(context)
        database = databaseHelper.writableDatabase
    }

    fun addEvent(value: Double, date: Date = Date(), description: String? = null): Event? {
        val values = ContentValues()
        values.put(EventDatabaseHelper.Event.Columns.DATE, date.dateAsSeconds)
        values.put(EventDatabaseHelper.Event.Columns.VALUE, value)
        values.put(EventDatabaseHelper.Event.Columns.DESCRIPTION, description)

        val rowId = database.insert(EventDatabaseHelper.Event.Table.NAME, null, values)

        if (rowId != -1L) {
            return Event(date, value, description)
        } else {
            return null
        }
    }
}
