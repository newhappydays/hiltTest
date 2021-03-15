package com.caru.hilttest.repository

import com.caru.hilttest.model.User
import kotlinx.coroutines.flow.Flow

class UserRepository (
    private val userDao: UserDao
) {

    val userList: Flow<List<User>> = userDao.getUserList()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }
}