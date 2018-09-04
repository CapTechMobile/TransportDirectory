package com.captechventures.transportdirectory.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.captechventures.transportdirectory.utilities.DATABASE_NAME
import com.captechventures.transportdirectory.workers.PopulateDatabaseWorker

/**
 * The Room database for this app
 */
@Database(entities = [Car::class], version = 1, exportSchema = false)
abstract class TransportDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {

        //Singleton Instantiation
        @Volatile
        private var instance: TransportDatabase? = null

        fun getInstance(context: Context): TransportDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database.
        private fun buildDatabase(context: Context): TransportDatabase {
            return Room.databaseBuilder(context, TransportDatabase::class.java, DATABASE_NAME)
                    //In this example, migration is not provided. So database will be cleared on upgrade.
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val request = OneTimeWorkRequestBuilder<PopulateDatabaseWorker>().build()
                            WorkManager.getInstance().enqueue(request)
                        }
                    })
                    .build()
        }
    }
}