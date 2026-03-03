package com.codepath.campgrounds

import android.app.Application
import androidx.room.Room

class CampgroundApplication : Application() {

    val db: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "campgrounds-db"
        ).build()
    }
}