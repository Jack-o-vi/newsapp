package com.chisw.data.db.entities

import android.net.Uri

interface DatabaseContract {
    val createTableSql: String
    val tableName: String
    val id: String
    val contentUri: Uri
}