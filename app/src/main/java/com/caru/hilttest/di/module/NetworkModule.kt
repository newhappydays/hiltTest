package com.caru.hilttest.di.module

import android.content.Context
import com.caru.hilttest.repository.MyService
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    fun provideMyService(@ApplicationContext context : Context) : MyService {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.8:8080/")
            .client(provideOkHttpClient(provideLoggingInterceptor(), context))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyService::class.java)
    }

    // 네트뭐크 통신에 사용할 클라이언트 객체를 생성합니다.
    private fun provideOkHttpClient(interceptor: HttpLoggingInterceptor, context: Context): OkHttpClient {
        val b = OkHttpClient.Builder()
            .connectTimeout(30000, java.util.concurrent.TimeUnit.MILLISECONDS)
            .writeTimeout(30000, java.util.concurrent.TimeUnit.MILLISECONDS)
            .readTimeout(30000, java.util.concurrent.TimeUnit.MILLISECONDS)

        // 이 클라이언트를 통해 오고 가는 네트워크 요청/응답을 로그로 표시하도록 합니다.
        b.addInterceptor(interceptor)
        val cookieJar = PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(context))
        b.cookieJar(cookieJar)

        return b.build()
    }

    // 네트워크 요청/응답을 로그에 표시하는 Interceptor 객체를 생성합니다.
    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return interceptor
    }
}