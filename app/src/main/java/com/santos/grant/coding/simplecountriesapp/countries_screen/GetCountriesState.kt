package com.santos.grant.coding.simplecountriesapp.countries_screen

import com.santos.grant.coding.simplecountriesapp.common.data.local.entity.CountryEntity

data class GetCountriesState(
    val isLoading : Boolean = false,
    val countries: List<CountryEntity>? = null,
    val error: String = ""
)