package com.example.mynavigation

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mynavigation.entity.User
import com.example.mynavigation.entity.UserDao

@Database(entities = [User::class],version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun buildDatabase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context,AppDatabase::class.java,
                        "users_db").allowMainThreadQueries().build() }

            return INSTANCE
        }
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build().also { INSTANCE = it }
            }
        }
    }
}