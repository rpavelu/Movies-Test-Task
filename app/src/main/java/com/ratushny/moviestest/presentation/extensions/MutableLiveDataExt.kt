package com.ratushny.moviestest.presentation.extensions

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<T>.update(updateAction: T.() -> T) {
    value = value?.let { updateAction(it) }
}