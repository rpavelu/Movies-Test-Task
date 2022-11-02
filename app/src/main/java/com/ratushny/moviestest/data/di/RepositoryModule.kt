package com.ratushny.moviestest.data.di

import com.ratushny.moviestest.data.repository.MoviesRepository
import com.ratushny.moviestest.domain.api.MoviesRepositoryApi
import org.koin.dsl.module

val repositoryModule = module {
    factory<MoviesRepositoryApi> {
        MoviesRepository(
            service = get(),
        )
    }
}