package com.example.ikpmd

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "JudoMovesDatabase"
        private const val TABLE_JUDO_WAZA = "JudoWazaTable"

        private const val WAZA_ID = "waza_id"
        private const val WAZA_NAME = "waza_name"
        private const val WAZA_PIC = "waza_pic"
        private const val WAZA_DESCRIPTION = "description"
        private const val KEY_DATE = "date"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //creating table with fields
        val createTableJudoWaza = ("CREATE TABLE " + TABLE_JUDO_WAZA + "("
                + WAZA_ID + " INTEGER PRIMARY KEY,"
                + WAZA_NAME + " TEXT,"
                + WAZA_PIC + " TEXT,"
                + WAZA_DESCRIPTION + " TEXT,"
                + KEY_DATE + " TEXT)")
        db?.execSQL(createTableJudoWaza)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_JUDO_WAZA")
        onCreate(db)
    }

    fun addHappyPlace(waza: Waza): Long {
        val db = this.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(WAZA_NAME, waza.waza_name) // HappyPlaceModelClass TITLE
        contentValues.put(WAZA_PIC, waza.waza_pic) // HappyPlaceModelClass IMAGE
        contentValues.put(WAZA_DESCRIPTION, waza.description) // HappyPlaceModelClass DESCRIPTION
//        contentValues.put(KEY_DATE, waza.date) // HappyPlaceModelClass DATE

        // Inserting Row
        val result = db.insert(TABLE_JUDO_WAZA, null, contentValues)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return result
    }
}