package com.chisw.data.db.manager

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import javax.inject.Inject

object DBManager {

    var sqlOpenDBHelper: SQLiteOpenHelper? = null
        @Inject set

    var db: SQLiteDatabase? = null
        @Inject set

    fun openDb() {
        db = sqlOpenDBHelper?.writableDatabase
    }

    fun closeDb(){
        sqlOpenDBHelper?.close()
    }


}