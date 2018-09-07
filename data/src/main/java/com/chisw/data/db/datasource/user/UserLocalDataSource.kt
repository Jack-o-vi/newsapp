package com.chisw.data.db.datasource.user

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.chisw.data.db.datasource.AbstractLocalDataSource
import com.chisw.data.db.datasource.DataSource
import com.chisw.data.db.entities.DatabaseContract
import com.chisw.data.db.mapper.Mapper
import com.chisw.data.db.model.user.User
import com.chisw.data.db.specification.Specification
import com.chisw.data.db.specification.SqlSpecification
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class UserLocalDataSource @Inject constructor(sqLiteDatabase: SQLiteDatabase?,
                                              contract: DatabaseContract,
                                              userContentValuesMapper: Mapper<ContentValues, User>,
                                              userCursorMapper: Mapper<User, Cursor>) : AbstractLocalDataSource<User>(
        sqLiteDatabase,
        contract,
        userContentValuesMapper,
        userCursorMapper) {

    override fun add(data: User): Long {
        return add(Collections.singleton(data)).elementAt(0)
    }

    override fun add(data: Iterable<User>): Iterable<Long> {

        val list = ArrayList<Long>()

        sqLiteDatabase?.let {
            it.beginTransaction()
            try {
                for (item in data) {
                    list.add(it.insert(contract.tableName, null, userContentValuesMapper.map(item)))
                }
                it.setTransactionSuccessful()
            } finally {
                it.endTransaction()
            }
        }
        return list
    }

    override fun remove(data: User, specification: Specification): Int {
        return remove(Collections.singleton(data), specification).elementAt(0)
    }

    override fun remove(data: Iterable<User>, specification: Specification): Iterable<Int> {
        val list = ArrayList<Int>()

        sqLiteDatabase?.let {
            it.beginTransaction()
            try {
                if (specification is SqlSpecification) {
                    for (item in data) {
                        list.add(it.delete(contract.tableName, specification.selection(), specification.selectionArgs()))
                    }
                }
                it.setTransactionSuccessful()
            } finally {
                it.endTransaction()
            }
        }
        return list
    }

    override fun update(data: User, specification: Specification): Int {
        return update(Collections.singleton(data), specification).elementAt(0)
    }

    override fun update(data: Iterable<User>, specification: Specification): Iterable<Int> {
        val list = ArrayList<Int>()

        sqLiteDatabase?.let {
            it.beginTransaction()
            try {
                if (specification is SqlSpecification) {
                    for (item in data) {
                        list.add(it.update(contract.tableName, userContentValuesMapper.map(item), specification.selection(), specification.selectionArgs()))
                    }
                }
                it.setTransactionSuccessful()
            } finally {
                it.endTransaction()
            }
        }
        return list
    }

    override fun query(callback: DataSource.OnDataCallback<User>, specification: Specification) {
        sqLiteDatabase?.let {
            it.beginTransaction()
            try {
                if (specification is SqlSpecification) {
                    it.query(contract.tableName,
                            specification.projection(),
                            specification.selection(),
                            specification.selectionArgs(),
                            null,
                            null,
                            specification.sortOrder())?.use { cursor ->
                        if (!cursor.isClosed && cursor.moveToFirst() && cursor.moveToNext()) {
                            callback.onDataListLoaded(userCursorMapper.map(cursor))
                            return
                        } else {
                            callback.onFailed()
                        }
                    }
                    callback.onFailed()
                }
                it.setTransactionSuccessful()
            } finally {
                it.endTransaction()
            }
        }
    }

    override fun query(callback: DataSource.OnDataListCallback<User>, specification: Specification) {
        sqLiteDatabase?.let {
            it.beginTransaction()
            try {
                if (specification is SqlSpecification) {
                    it.query(contract.tableName,
                            specification.projection(),
                            specification.selection(),
                            specification.selectionArgs(),
                            null,
                            null,
                            specification.sortOrder())?.use { cursor ->
                        if (!cursor.isClosed && cursor.moveToFirst()) {
                            val listUser = ArrayList<User>()
                            do {
                                listUser.add(userCursorMapper.map(cursor))
                            } while (cursor.moveToNext())
                            callback.onDataListLoaded(listUser)
                            return
                        } else {
                            callback.onFailed()
                        }
                    }

                    callback.onFailed()
                }
                it.setTransactionSuccessful()
            } finally {
                it.endTransaction()
            }
        }
    }
}