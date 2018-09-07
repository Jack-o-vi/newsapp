package com.chisw.data.db.specification

import com.chisw.data.db.entities.DatabaseContract

class DefaultSqlSpecification(var id: Long? = null,
                              private var contract: DatabaseContract? = null) : SqlSpecification {
    override fun having(): String? {
        return null
    }

    override fun groupBy(): String? {
        return null
    }

    override fun projection(): Array<String>? {
        return null
    }

    override fun selectionArgs(): Array<String>? {
        return id?.let { id ->
            Array(1) { id.toString() }
        }
    }

    override fun selection(): String? {
        return id?.let { id ->
            contract?.let {
                return it.id
            }
        }
    }

    override fun sortOrder(): String? {
        return null
    }
}
