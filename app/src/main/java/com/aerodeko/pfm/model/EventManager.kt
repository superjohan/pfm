package com.aerodeko.pfm.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

/**
 * Created by rm on 10/12/2016.
 */

class EventManager(context: Context) {
    val database: SQLiteDatabase

    init {
        val databaseHelper = EventDatabaseHelper(context)
        database = databaseHelper.writableDatabase
    }

    fun addEvent(event: Event) {
        val values = ContentValues()
        values.put(EventDatabaseHelper.Event.Columns.DATE, event.dateAsSeconds)
        values.put(EventDatabaseHelper.Event.Columns.VALUE, event.value)
        values.put(EventDatabaseHelper.Event.Columns.DESCRIPTION, event.description)

        database.insert(EventDatabaseHelper.Event.Table.NAME, null, values)
    }
}
