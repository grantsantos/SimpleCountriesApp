package com.santos.grant.coding.simplecountriesapp.common.data.network.dto

import com.google.gson.annotations.SerializedName

data class CountryNameDto(

    @SerializedName("common")
    val common: String,

    @SerializedName("official")
    val official: String

)
