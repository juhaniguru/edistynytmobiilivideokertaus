package com.example.androidvideokertaus

import android.app.Application

class CustomApp: Application() {
    override fun onCreate() {
        super.onCreate()
        DbProvider.provide(this)
    }
}