package com.santos.grant.coding.simplecountriesapp.common.data

import android.util.Log
import com.santos.grant.coding.simplecountriesapp.common.data.local.database.AppDatabase
import com.santos.grant.coding.simplecountriesapp.common.data.local.entity.CountryEntity
import com.santos.grant.coding.simplecountriesapp.common.data.network.api.RestCountriesApi
import com.santos.grant.coding.simplecountriesapp.common.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CountriesRepository @Inject constructor(
    private val api: RestCountriesApi,
    private val db: AppDatabase
) {
    private val countriesDao = db.countriesDao

    fun getAllCountries() : Flow<Resource<List<CountryEntity>>> = flow {
        val oldCachedCountries = countriesDao.getAllCountries()
        emit(Resource.Loading(data = oldCachedCountries))

        try {
            val countriesEntity = api.getAllCountries().map {
                it.toCountryEntity()
            }
            countriesDao.deleteAndInsertCountries(countriesEntity)
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage ?: "Network Error, could not load the data"))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Server Error, could not load the data"))
        }

        val newCachedCountries = countriesDao.getAllCountries()
        emit(Resource.Success(data = newCachedCountries))
    }


}