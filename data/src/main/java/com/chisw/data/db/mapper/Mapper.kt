package com.chisw.data.db.mapper

interface Mapper<T, F> {
    fun map(from: F): T
}