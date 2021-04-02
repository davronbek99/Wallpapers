package com.example.a4kfullwalpapers.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ImageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    companion object {
        private var INSTANSE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            if (INSTANSE == null) {
                INSTANSE = Room.databaseBuilder(context, AppDatabase::class.java, "image")
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANSE as AppDatabase
        }
    }

    abstract fun imageDao(): ImageDao
}