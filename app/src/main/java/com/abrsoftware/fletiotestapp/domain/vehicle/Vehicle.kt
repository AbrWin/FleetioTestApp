package com.abrsoftware.fletiotestapp.domain.vehicle
import com.abrsoftware.fletiotestapp.data.model.VehicleModel


data class Vehicle(
    val id: Int?,
    val vehicle_status_color: String?,
    val default_image_url: String?,
    val vehicle_status_name: String?
)

fun VehicleModel.toDomain() = Vehicle(id, vehicle_status_color, default_image_url, vehicle_status_name)

