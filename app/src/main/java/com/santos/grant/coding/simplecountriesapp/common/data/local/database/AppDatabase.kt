package com.santos.grant.coding.simplecountriesapp.common.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.santos.grant.coding.simplecountriesapp.common.data.local.dao.CountriesDao
import com.santos.grant.coding.simplecountriesapp.common.data.local.entity.CountryEntity

@Database(
    entities = [
        CountryEntity::class,
    ], version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract val countriesDao: CountriesDao
}