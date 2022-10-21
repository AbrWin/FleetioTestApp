package com.abrsoftware.fletiotestapp.domain.vehicle
import android.os.Parcelable
import com.abrsoftware.fletiotestapp.data.model.VehicleModel
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Vehicle(
    val id: Int?,
    val vehicle_status_color: String?,
    val default_image_url: String?,
    val vehicle_status_name: String?
) : Parcelable

fun VehicleModel.toDomain() = Vehicle(id, vehicle_status_color, default_image_url, vehicle_status_name)

