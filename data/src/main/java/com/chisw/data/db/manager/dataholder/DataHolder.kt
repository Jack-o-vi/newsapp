package com.chisw.data.db.manager.dataholder

import android.net.Uri

interface DataHolder {
    fun getContentUri(): Uri
    fun getTargetItem(): String
    fun getTableName(): String
}