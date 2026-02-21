package com.abrsoftware.fletiotestapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.abrsoftware.fletiotestapp.data.local.entity.VehicleEntity
import com.abrsoftware.fletiotestapp.data.local.entity.CommentEntity

@Database(
    entities = [VehicleEntity::class, CommentEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vehicleDao(): VehicleDao
    abstract fun commentDao(): CommentDao

    companion object {
        const val DATABASE_NAME = "fleetio_database"
    }
}
