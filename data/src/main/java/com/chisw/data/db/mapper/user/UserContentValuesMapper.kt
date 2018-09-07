package com.chisw.data.db.mapper.user

import android.content.ContentValues
import com.chisw.data.db.entities.DatabaseContract
import com.chisw.data.db.entities.user.UserContract
import com.chisw.data.db.mapper.Mapper
import com.chisw.data.db.model.user.User

class UserContentValuesMapper (private val databaseContract: DatabaseContract) : Mapper<ContentValues, User> {
    override fun map(from: User): ContentValues {
        val contentValues = ContentValues()
        if(databaseContract is UserContract){
            contentValues.put(databaseContract.email, from.email)
            contentValues.put(databaseContract.name, from.name)
            contentValues.put(databaseContract.surname, from.surname)
        }
        return contentValues
    }
}