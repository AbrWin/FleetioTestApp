package com.abrsoftware.fletiotestapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abrsoftware.fletiotestapp.data.local.entity.CommentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(comment: CommentEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(comments: List<CommentEntity>)

    @Delete
    suspend fun delete(comment: CommentEntity)

    @Query("SELECT * FROM comments WHERE vehicleId = :vehicleId ORDER BY timestamp DESC")
    fun getCommentsByVehicleId(vehicleId: String): Flow<List<CommentEntity>>

    @Query("SELECT * FROM comments WHERE vehicleId = :vehicleId ORDER BY timestamp DESC")
    suspend fun getCommentsByVehicleIdAsync(vehicleId: String): List<CommentEntity>

    @Query("DELETE FROM comments WHERE vehicleId = :vehicleId")
    suspend fun deleteByVehicleId(vehicleId: String)

    @Query("DELETE FROM comments")
    suspend fun deleteAll()
}
