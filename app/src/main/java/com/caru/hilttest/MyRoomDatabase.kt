package com.caru.hilttest

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.caru.hilttest.model.User
import com.caru.hilttest.repository.UserDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        val DB_NAME = "my_database"
    }
}