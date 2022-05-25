package com.santos.grant.coding.simplecountriesapp.common.data.local.dao

import androidx.room.*
import com.santos.grant.coding.simplecountriesapp.common.data.local.entity.CountryEntity

@Dao
interface CountriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCountries(countries: List<CountryEntity>)

    @Query("SELECT * FROM CountryEntity")
    suspend fun getAllCountries(): List<CountryEntity>

    @Query("DELETE FROM CountryEntity")
    suspend fun deleteAllCountries()

    @Transaction
    suspend fun deleteAndInsertCountries(countries: List<CountryEntity>) {
        deleteAllCountries()
        saveCountries(countries)
    }
}