package com.chisw.data.db.manager

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.chisw.data.db.entities.DatabaseContract
import javax.inject.Inject

class DBHelper @Inject constructor(context: Context,
                                   private var contracts: List<DatabaseContract>? = null)
    : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        var DB_NAME = "my_db_name"
        var DB_VERSION = 1
    }

    private val tag = DBHelper::class.java.simpleName!!

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(tag, "DB_NAME = $DB_NAME, VERSION = $DB_VERSION")
        contracts?.also {
            for (item in it) {
                db?.execSQL(item.createTableSql)
            }
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}