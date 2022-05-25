package com.santos.grant.coding.simplecountriesapp.common.data.network.dto

import com.google.gson.annotations.SerializedName

data class CurrencyDto(

    @SerializedName("name")
    val currencyName: String,

    @SerializedName("symbol")
    val currencySymbol: String

)