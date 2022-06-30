package com.example.ikpmd

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "JudoMovesDatabase"
        private const val TABLE_JUDO_WAZA = "JudoWazaTable"

        private const val WAZA_ID = "waza_id"
        private const val WAZA_NAME = "waza_name"
        private const val WAZA_DESCRIPTION = "description"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val createTableJudoWaza = ("CREATE TABLE " + TABLE_JUDO_WAZA + "("
                + WAZA_ID + " INTEGER PRIMARY KEY,"
                + WAZA_NAME + " TEXT,"
                + WAZA_DESCRIPTION + " TEXT)")
        db?.execSQL(createTableJudoWaza)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_JUDO_WAZA")
        Log.e("String", "UPGRADED!!!")
        onCreate(db)
    }

    fun addWazaToTheDatabase(waza: Waza): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(WAZA_NAME, waza.waza_name) // Name
        contentValues.put(WAZA_DESCRIPTION, waza.description) // DESCRIPTION

        // Inserting Row
        val result = db.insert(TABLE_JUDO_WAZA, null, contentValues)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return result
    }

    fun getJudoWazaList(): ArrayList<Waza> {
        val wazaList: ArrayList<Waza> = ArrayList()
        val selectQuery = "SELECT * FROM $TABLE_JUDO_WAZA"
        val db = this.readableDatabase

        try {
            val cursor: Cursor = db.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val waza = Waza(
                        cursor.getInt(cursor.getColumnIndexOrThrow(WAZA_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(WAZA_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(WAZA_DESCRIPTION))
                    )
                    wazaList.add(waza)

                } while (cursor.moveToNext())
            }
            cursor.close()
        } catch (e: SQLiteException) {
            db.execSQL(selectQuery)
            return ArrayList()
        }
        return wazaList
    }
}