package com.chisw.data.db.entities.user

import android.net.Uri
import com.chisw.data.BuildConfig
import com.chisw.data.db.entities.DatabaseContract

class UserContract : DatabaseContract {

    val name = "name"

    val surname = "surname"

    val email = "email"

    override val id = "_id"

    override val tableName: String = "users"

    override val contentUri = Uri.parse("content://${BuildConfig.CONTENT_URI_AUTHORITY}/$tableName")!!

    override val createTableSql = "CREATE TABLE IF NOT EXISTS $tableName" +
            "($id integer, " +
            "$email text, " +
            "$name text," +
            "$surname text )"
}