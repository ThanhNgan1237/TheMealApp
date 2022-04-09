package com.ngan.themealapp.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ngan.themealapp.data.model.MealDetail

@Database(
    entities = [MealDetail::class],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase: RoomDatabase() {

    abstract fun getMealDao(): MealDao


}