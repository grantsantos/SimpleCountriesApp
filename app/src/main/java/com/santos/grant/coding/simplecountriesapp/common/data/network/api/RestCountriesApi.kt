package com.santos.grant.coding.simplecountriesapp.common.data.network.api

import com.santos.grant.coding.simplecountriesapp.common.data.network.dto.CountryDto
import retrofit2.http.GET

interface  RestCountriesApi {

    companion object {
        const val BASE_URL = "https://restcountries.com/"
    }

    @GET("v3.1/all")
    suspend fun getAllCountries(): List<CountryDto>

}