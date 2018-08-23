package com.example.rawan.mykotlin.db

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by rawan on 8/14/18.
 */
class HabitHelperDb(context: Context):SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION){
    private val Sql_CREATE="CREATE TABLE ${HabitEntry.TABLE_NAME} ( "+"${HabitEntry.ID_COL} INTEGER PRIMARY KEY AUTOINCREMENT,"+
            "${HabitEntry.TITLE_COL} TEXT,"+
            "${HabitEntry.DESCRIPTION_COL} TEXT,"+
            "${HabitEntry.IMAGE_COL} BLOB"+
             ")"
    private val SQL_DELETE = "DROP TABLE IF EXISTS ${HabitEntry.TABLE_NAME}"
    override fun onCreate(db: SQLiteDatabase) {
db.execSQL(Sql_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
    }

}