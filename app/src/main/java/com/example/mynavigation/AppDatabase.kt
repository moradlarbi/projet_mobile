package com.example.mynavigation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getUserDao():UserDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context,AppDatabase::class.java,
                        "users_db").allowMainThreadQueries().build() }

            return INSTANCE
        }
    }
}