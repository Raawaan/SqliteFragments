package com.example.rawan.mykotlin.db

import android.provider.BaseColumns

/**
 * Created by rawan on 8/14/18.
 */
val DATABASE_NAME="neHaittrainer.db"
val DATABASE_VERSION = 10
//baseColums -> when defining a schema of a table db
object HabitEntry :BaseColumns{
    val TABLE_NAME="neHabit"
    val ID_COL="id"
    val TITLE_COL="title"
    val DESCRIPTION_COL="description"
    val IMAGE_COL="image"
}