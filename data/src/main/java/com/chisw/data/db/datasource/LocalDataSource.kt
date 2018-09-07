package com.chisw.data.db.datasource

import com.chisw.data.db.specification.Specification

interface LocalDataSource<T> : DataSource {

    fun add(data: T) : Long

    fun add(data: Iterable<T>) : Iterable<Long>

    fun remove(data: T, specification: Specification) : Int

    fun remove(data: Iterable<T>, specification: Specification) : Iterable<Int>

    fun update(data: T, specification: Specification) : Int

    fun update(data: Iterable<T>, specification: Specification): Iterable<Int>

    fun query(callback: DataSource.OnDataCallback<T>, specification: Specification)

    fun query(callback: DataSource.OnDataListCallback<T>, specification: Specification)
}
