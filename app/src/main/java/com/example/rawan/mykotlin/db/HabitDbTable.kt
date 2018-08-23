package com.example.rawan.mykotlin.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.BitmapRegionDecoder
import com.example.rawan.mykotlin.DataClass
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

/**
 * Created by rawan on 8/14/18.
 */
class HabitDbTable(context: Context){
    private val dbHelper= HabitHelperDb(context)
    fun store(habit:DataClass):Long{
    val db = dbHelper.writableDatabase
        val values = ContentValues()
        with(values){
        put(HabitEntry.TITLE_COL,habit.title)
        put(HabitEntry.DESCRIPTION_COL,habit.description)
        put(HabitEntry.IMAGE_COL,toByteArray(habit.image))}
        val id= db.transaction {
            insert(HabitEntry.TABLE_NAME,null,values)
        }
        return id
    }
    fun ReadAllData():List<DataClass>{
        val colums = arrayOf(HabitEntry.TITLE_COL,HabitEntry.DESCRIPTION_COL,HabitEntry.IMAGE_COL)
        val db= dbHelper.readableDatabase
      var cursor=db.query(HabitEntry.TABLE_NAME,colums,null,null,null,null,null)
        val habits= mutableListOf<DataClass>()
        if (cursor != null) {
            while (cursor.moveToNext()){
            var title= cursor.getString(cursor.getColumnIndex(HabitEntry.TITLE_COL))
            var desc=cursor.getString(cursor.getColumnIndex(HabitEntry.DESCRIPTION_COL))
            var imgByteArray = cursor.getBlob(cursor.getColumnIndex(HabitEntry.IMAGE_COL))
            var bitmap= BitmapFactory.decodeByteArray(imgByteArray,0,imgByteArray.size)
            habits.add(DataClass(title,desc,bitmap))
        }}
        cursor.close()
        db.close()
        return habits
    }

    private fun toByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG,0,stream)
        return  stream.toByteArray()

    }
}//no more arguments
private inline fun<T> SQLiteDatabase.transaction (function:SQLiteDatabase.()->T):T{
    beginTransaction()
    val value = try{
       val result= this.function()
        setTransactionSuccessful()
        result
        }
    finally {
        endTransaction()
    }
    close()
    return value
}