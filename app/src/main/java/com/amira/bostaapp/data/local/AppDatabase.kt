package com.amira.bostaapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.amira.bostaapp.data.local.entities.AlbumTable
import com.amira.bostaapp.data.local.entities.ImageTable
import com.amira.bostaapp.data.local.entities.UserTable


/**
 * The Room database for the app
 */

@Database(
    entities = [
        UserTable::class,
        AlbumTable::class,
        ImageTable::class
    ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)

abstract class AppDatabase : RoomDatabase() {
    abstract val appDao: AppDao

    companion object {
        const val DATABASE_NAME = "BostaDatabase"
    }
}



