package com.santos.grant.coding.simplecountriesapp.common.data.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.santos.grant.coding.simplecountriesapp.common.data.local.entity.CountryEntity

data class CountryDto(

    @SerializedName("name")
    val name: CountryNameDto,
    
    
    @SerializedName("capital")
    val capital: List<String>?,

    @SerializedName("currencies")
    @Expose
    val currencies: Map<String, CurrencyDto>?,

    @SerializedName("languages")
    @Expose
    val languages: Map<String, String>?,


    @SerializedName("flags")
    val flags: FlagsDto
) {
    fun toCountryEntity() : CountryEntity {
        return CountryEntity(
            commonCountryName = name.common ?: "",
            officialCountryName = name.official ?: "",
            flagImageUrl = flags.png,
            languages = languages?.values?.toList() ?: emptyList(),
            capital =  capital?.getOrNull(0) ?: "N/A",
            currency =  currencies?.values?.toList()?.getOrNull(0)?.currencyName ?: "N/A",
            currencySymbol = currencies?.values?.toList()?.getOrNull(0)?.currencySymbol ?: "N/A"
        )
    }
}