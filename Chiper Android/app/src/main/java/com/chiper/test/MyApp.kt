package com.chiper.test

import android.app.Application
import android.content.Context
import com.chiper.test.room.ChiperDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class MyApp: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { ChiperDatabase.getDB(this, applicationScope) }
    val repository by lazy { MovieRepository(database.moviesDao()) }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: MyApp
        val context: Context get() {
                return instance.applicationContext
            }
    }
}