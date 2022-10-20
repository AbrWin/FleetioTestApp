package com.abrsoftware.fletiotestapp.di

import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideVehicleTestApi(): FleetioTestApi {
        return Retrofit.Builder()
            .baseUrl("https://secure.fleetio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}