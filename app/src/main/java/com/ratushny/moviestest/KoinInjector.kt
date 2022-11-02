package com.ratushny.moviestest

import android.content.Context
import com.ratushny.moviestest.data.di.networkModule
import com.ratushny.moviestest.data.di.repositoryModule
import com.ratushny.moviestest.domain.di.interactorModule
import com.ratushny.moviestest.presentation.di.movieInfoFragmentModule
import com.ratushny.moviestest.presentation.di.movieListFragmentModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module

object KoinInjector {

    fun initKoin(applicationContext: Context) {
        startKoin {
            androidContext(applicationContext)
            androidLogger(if (BuildConfig.DEBUG) Level.DEBUG else Level.INFO)
            modules(
                dataLayerModules,
                domainLayerModules,
                presentationLayerModules,
            )
        }
    }

    private val dataLayerModules = module {
        includes(
            networkModule,
            repositoryModule,
        )
    }

    private val domainLayerModules = module {
        includes(
            interactorModule,
        )
    }

    private val presentationLayerModules = module {
        includes(
            movieListFragmentModule,
            movieInfoFragmentModule,
        )
    }
}