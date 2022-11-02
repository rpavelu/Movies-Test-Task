package com.ratushny.moviestest.data.di

import com.ratushny.moviestest.BuildConfig
import com.ratushny.moviestest.data.network.MoviesServiceV3
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

private const val TIMEOUT = 20L

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BuildConfig.MOVIES_LIST_URL + BuildConfig.MOVIES_LIST_API_VER_3)
            .client(get())
            .build()
            .create(MoviesServiceV3::class.java)
    }
}
