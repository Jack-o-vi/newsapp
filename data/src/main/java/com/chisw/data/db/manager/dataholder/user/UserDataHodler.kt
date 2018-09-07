package com.chisw.data.db.manager.dataholder.user

import android.net.Uri
import com.chisw.data.db.entities.DatabaseContract
import com.chisw.data.db.manager.dataholder.DataHolder
import javax.inject.Inject

class UserDataHodler @Inject constructor(private val databaseContract: DatabaseContract) : DataHolder {

    override fun getContentUri(): Uri {
        return databaseContract.contentUri
    }

    override fun getTargetItem(): String {
        return databaseContract.id
    }

    override fun getTableName(): String {
        return databaseContract.tableName
    }
}