package com.caru.hilttest.di.module

import com.caru.hilttest.repository.UserDao
import com.caru.hilttest.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideUserRepository(
        userDao: UserDao
    ) : UserRepository = UserRepository(userDao)

}