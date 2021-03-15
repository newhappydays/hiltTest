package com.caru.hilttest

import android.app.Application
import com.caru.hilttest.repository.UserRepository
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application() {}