package com.chisw.data.db.repository.user

import com.chisw.data.db.datasource.DataSource
import com.chisw.data.db.datasource.LocalDataSource
import com.chisw.data.db.model.user.User
import com.chisw.data.db.repository.Repository
import com.chisw.data.db.specification.DefaultSqlSpecification

import io.reactivex.Observable
import java.util.concurrent.CountDownLatch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
object UserRepository : Repository<User> {

    private var localDataSource: LocalDataSource<User>? = null
        @Inject set

    private var countDownLatch: CountDownLatch? = null
        @Inject set

    fun getUsers(): List<User>? {
        var list: ArrayList<User>? = null
        countDownLatch = CountDownLatch(2)
        localDataSource?.query(object : DataSource.OnDataListCallback<User> {

            override fun onDataListLoaded(data: List<User>?) {
                list = ArrayList(data)
                countDownLatch?.countDown()
            }

            override fun onFailed() {
                countDownLatch?.countDown()
            }
        }, DefaultSqlSpecification())
        countDownLatch?.countDown()
        countDownLatch?.await()

        return list
    }

    fun users(): Observable<List<User>>? {
        var observable: Observable<List<User>>?

        localDataSource?.query(object : DataSource.OnDataListCallback<User> {

            override fun onDataListLoaded(data: List<User>?) {
                return
            }

            override fun onFailed() {

            }
        }, DefaultSqlSpecification())
        return null
    }

}
