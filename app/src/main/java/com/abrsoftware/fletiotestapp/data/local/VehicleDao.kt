package com.abrsoftware.fletiotestapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abrsoftware.fletiotestapp.data.local.entity.VehicleEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vehicle: VehicleEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vehicles: List<VehicleEntity>)

    @Delete
    suspend fun delete(vehicle: VehicleEntity)

    @Query("DELETE FROM vehicles WHERE vehicleId = :vehicleId")
    suspend fun deleteById(vehicleId: String)

    @Query("SELECT * FROM vehicles WHERE vehicleId = :vehicleId LIMIT 1")
    suspend fun getVehicleById(vehicleId: String): VehicleEntity?

    @Query("SELECT * FROM vehicles ORDER BY syncTimestamp DESC")
    fun getAllVehicles(): Flow<List<VehicleEntity>>

    @Query("DELETE FROM vehicles")
    suspend fun deleteAll()

    @Query("SELECT * FROM vehicles WHERE syncTimestamp < :timestamp")
    suspend fun getOldVehicles(timestamp: Long): List<VehicleEntity>
}
