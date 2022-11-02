package com.ratushny.moviestest.domain.di

import com.ratushny.moviestest.domain.interactor.MoviesInteractor
import org.koin.dsl.module

val interactorModule = module {
    factory { MoviesInteractor(repository = get()) }
}