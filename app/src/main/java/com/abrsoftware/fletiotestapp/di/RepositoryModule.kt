package com.abrsoftware.fletiotestapp.di

import com.abrsoftware.fletiotestapp.data.repository.CommentRepositoryImp
import com.abrsoftware.fletiotestapp.data.repository.VehicleRepositoryImpl
import com.abrsoftware.fletiotestapp.domain.repository.CommentRepository
import com.abrsoftware.fletiotestapp.domain.repository.VehicleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVehicleListRepository(
        vehicleRepositoryImpl: VehicleRepositoryImpl
    ): VehicleRepository


    @Binds
    @Singleton
    abstract fun bindCommentListRepository(
        vehicleRepositoryImpl: CommentRepositoryImp
    ): CommentRepository
}