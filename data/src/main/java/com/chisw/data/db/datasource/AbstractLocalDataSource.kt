package com.chisw.data.db.datasource

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.chisw.data.db.entities.DatabaseContract
import com.chisw.data.db.mapper.Mapper
import com.chisw.data.db.model.user.User

abstract class AbstractLocalDataSource<T> (
        protected var sqLiteDatabase: SQLiteDatabase?,
        protected var contract: DatabaseContract,
        protected var userContentValuesMapper: Mapper<ContentValues, User>,
        protected var userCursorMapper: Mapper<User, Cursor>) : LocalDataSource<T>