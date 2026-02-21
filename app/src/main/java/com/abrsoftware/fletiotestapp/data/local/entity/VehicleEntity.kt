package com.abrsoftware.fletiotestapp.data.local.entity

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "vehicles",
    indices = [Index(value = ["vehicleId"], unique = true)]
)
data class VehicleEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val vehicleId: String,
    val name: String,
    val vehicleType: String,
    val color: String,
    val fuelVolume: String,
    val ownership: String,
    val imageUrl: String?,
    val syncTimestamp: Long = System.currentTimeMillis()
)
