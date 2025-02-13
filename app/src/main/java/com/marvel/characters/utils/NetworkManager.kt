package com.marvel.characters.utils

import com.marvel.characters.di.BaseUrlInterceptor
import javax.inject.Inject

class NetworkManager @Inject constructor(private val baseUrlInterceptor: BaseUrlInterceptor) {

    fun changeBaseUrl(newUrl: String) {
        baseUrlInterceptor.setNewUrl(newUrl)
    }
}