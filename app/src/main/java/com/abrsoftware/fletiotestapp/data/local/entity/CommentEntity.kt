package com.abrsoftware.fletiotestapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(
    tableName = "comments",
    foreignKeys = [
        ForeignKey(
            entity = VehicleEntity::class,
            parentColumns = ["vehicleId"],
            childColumns = ["vehicleId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val vehicleId: String,
    val commentText: String,
    val author: String,
    val timestamp: Long = System.currentTimeMillis()
)
