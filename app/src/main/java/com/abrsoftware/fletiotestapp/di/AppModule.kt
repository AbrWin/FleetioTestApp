package com.abrsoftware.fletiotestapp.di

import android.content.Context
import androidx.room.Room
import com.abrsoftware.fletiotestapp.R
import com.abrsoftware.fletiotestapp.data.local.AppDatabase
import com.abrsoftware.fletiotestapp.data.local.VehicleDao
import com.abrsoftware.fletiotestapp.data.local.CommentDao
import com.abrsoftware.fletiotestapp.data.remote.FleetioTestApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Timber.tag("OkHttp").d(message)
        }.apply {
            // TODO: Usar BuildConfig.DEBUG cuando esté disponible
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideVehicleTestApi(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): FleetioTestApi {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.end_point))
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FleetioTestApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            AppDatabase.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideVehicleDao(database: AppDatabase): VehicleDao {
        return database.vehicleDao()
    }

    @Provides
    @Singleton
    fun provideCommentDao(database: AppDatabase): CommentDao {
        return database.commentDao()
    }
}
