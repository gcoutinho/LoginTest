package com.testproject

import android.app.Application
import com.testproject.di.AppComponent
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CustomApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // Initialize Dagger or any other dependency injection framework here
        AppComponent.inject(this)
    }
}