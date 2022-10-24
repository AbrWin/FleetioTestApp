package com.abrsoftware.fletiotestapp.data.model

import com.google.gson.annotations.SerializedName


data class VehicleModel(
    @SerializedName("id") val id: Int?,
    @SerializedName("vehicle_status_color") val vehicle_status_color: String?,
    @SerializedName("default_image_url") val default_image_url: String?,
    @SerializedName("vehicle_status_name") val vehicle_status_name: String?,
    @SerializedName("vehicle_type_name") val vehicle_type_name: String?,
    @SerializedName("fuel_volume_units") val fuel_volume_units: String?,
    @SerializedName("vehicle_status_id") val vehicle_status_id: String?,
    @SerializedName("ownership") val ownership: String?
)