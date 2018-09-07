package com.chisw.data.db.mapper.user

import android.database.Cursor
import com.chisw.data.db.entities.DatabaseContract
import com.chisw.data.db.entities.user.UserContract
import com.chisw.data.db.mapper.Mapper
import com.chisw.data.db.model.user.User

class UserCursorMapper(private val databaseContract: DatabaseContract) : Mapper<User, Cursor> {
    override fun map(from: Cursor): User {
        val user = User()
        if(databaseContract is UserContract){
            user.id = from.getLong(from.getColumnIndex(databaseContract.id))
            user.name = from.getString(from.getColumnIndex(databaseContract.name))
            user.surname = from.getString(from.getColumnIndex(databaseContract.surname))
            user.email = from.getString(from.getColumnIndex(databaseContract.email))
        }
        return user
    }
}