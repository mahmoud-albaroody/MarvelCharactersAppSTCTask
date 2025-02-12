package com.marvel.characters

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HiltApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

    }
}