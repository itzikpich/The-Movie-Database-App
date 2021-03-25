package com.itzikpich.moviesapp.di.modules

import com.itzikpich.moviesapp.data.remote.RetrofitService
import com.itzikpich.moviesapp.utilities.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    /**
     *  You can use the @Provides annotation in Dagger modules
     *  to tell Dagger how to provide classes
     *  that your project doesn't own
     *  (e.g. an instance of Retrofit).
     */

    @Singleton
    @Provides
    fun provideRetrofitService() : RetrofitService {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().apply {
//            if (BuildConfig.DEBUG) {
                addNetworkInterceptor(loggingInterceptor)
//            }
        }.build()

        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

}