package com.aerodeko.pfm.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

/**
 * Created by rm on 10/12/2016.
 */

internal class EventDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "events.db", null, Versions.CURRENT) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
                "CREATE TABLE " + Event.Table.NAME + " (" +
                        Event.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        Event.Columns.DATE + " TIMESTAMP NOT NULL," +
                        Event.Columns.VALUE + " REAL NOT NULL," +
                        Event.Columns.DESCRIPTION + " TEXT)"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        /**
         * Do nothing for now. This is the first version.
         */
    }

    private object Versions {
        val LAUNCH = 1
        val CURRENT = LAUNCH
    }

    object Event {
        object Table {
            val NAME = "events"
        }

        object Columns : BaseColumns {
            val ID = "id"
            val DATE = "date"
            val VALUE = "value"
            val DESCRIPTION = "description"
        }
    }
}
