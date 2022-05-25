package com.santos.grant.coding.simplecountriesapp.di

import android.app.Application
import androidx.room.Room
import com.santos.grant.coding.simplecountriesapp.common.data.CountriesRepository
import com.santos.grant.coding.simplecountriesapp.common.data.local.database.AppDatabase
import com.santos.grant.coding.simplecountriesapp.common.data.network.api.RestCountriesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRestCountriesApi(): RestCountriesApi {
        return Retrofit.Builder()
            .baseUrl(RestCountriesApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestCountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCountriesRepository(
        api: RestCountriesApi,
        db: AppDatabase
    ): CountriesRepository {
        return CountriesRepository(
            api = api,
            db = db
        )
    }

    @Provides
    @Singleton
    fun provideAppDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app,
            AppDatabase::class.java,
            "app_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

}