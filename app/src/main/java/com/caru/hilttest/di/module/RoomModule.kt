package com.caru.hilttest.di.module

import android.content.Context
import androidx.room.Room
import com.caru.hilttest.MyRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideUserDB(@ApplicationContext context : Context) =
        Room.databaseBuilder(
            context,
            MyRoomDatabase::class.java,
            MyRoomDatabase.DB_NAME
        )
            .fallbackToDestructiveMigration()
            .build()


    @Singleton
    @Provides
    fun provideUserDao(myRoomDatabase: MyRoomDatabase) = myRoomDatabase.userDao()
}