package com.aerodeko.pfm.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by rm on 10/12/2016.
 */

internal class EventDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "events.db", null, Versions.CURRENT) {
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
                    Budget.Columns.AMOUNT + " REAL NOT NULL," +
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
        const val LAUNCH = 1
        const val CURRENT = LAUNCH
    }

    object Event {
        object Table {
            const val NAME = "events"
        }

        object Columns {
            const val ID = "id"
            const val DATE = "date"
            const val VALUE = "value"
            const val DESCRIPTION = "description"
        }
    }

    object Budget {
        object Table {
            const val NAME = "budgets"
        }

        object Columns {
            const val ID = "id"
            const val NAME = "name"
            const val AMOUNT = "amount"
            const val START_DATE = "start_date"
            const val END_DATE = "end_date"
        }
    }
}
