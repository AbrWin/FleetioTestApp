package com.abrsoftware.fletiotestapp.domain.vehicle

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson

class AssetParamType : NavType<Vehicle>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): Vehicle? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Vehicle {
        return Gson().fromJson(value, Vehicle::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Vehicle) {
        bundle.putParcelable(key, value)
    }
}