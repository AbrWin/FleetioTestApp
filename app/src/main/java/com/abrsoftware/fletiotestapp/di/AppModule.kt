package com.abrsoftware.fletiotestapp.di

import android.content.Context
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun provideVehicleTestApi(@ApplicationContext context: Context): FleetioTestApi {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.end_point))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

}