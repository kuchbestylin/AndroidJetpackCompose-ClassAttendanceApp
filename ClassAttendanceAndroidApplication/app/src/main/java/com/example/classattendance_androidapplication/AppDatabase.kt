package com.example.classattendance_androidapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// This is the AppDatabase class, which extends RoomDatabase and serves as the main entry point for accessing the database.
@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    // This abstract function returns the UserDao, which provides access to user-related database operations.
    abstract fun getUserDao(): UserDao
    companion object {

        // We define a volatile instance variable to ensure its visibility across multiple threads.
        @Volatile
        private var instance : AppDatabase? = null

        // We define a volatile instance variable to ensure its visibility across multiple threads.
        private val LOCK = Any()

        // The invoke function allows us to create a new instance of the AppDatabase using the double-checked locking pattern.
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        // This private function builds and returns a new instance of the AppDatabase using the Room databaseBuilder.
        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            "app-database"
        ).build()
    }
}