package com.aerodeko.pfm.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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

        db?.execSQL(
                "CREATE TABLE " + Budget.Table.NAME + " (" +
                        Budget.Columns.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        Budget.Columns.NAME + " TEXT NOT NULL," +
                        Budget.Columns.START_DATE + " TIMESTAMP NOT NULL," +
                        Budget.Columns.END_DATE + " TIMESTAMP NOT NULL)"
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

        object Columns {
            val ID = "id"
            val DATE = "date"
            val VALUE = "value"
            val DESCRIPTION = "description"
        }
    }

    object Budget {
        object Table {
            val NAME = "budgets"
        }

        object Columns {
            val ID = "id"
            val NAME = "name"
            val START_DATE = "start_date"
            val END_DATE = "end_date"
        }
    }
}
