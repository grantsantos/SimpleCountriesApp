package com.santos.grant.coding.simplecountriesapp.common.data.network.dto

import com.google.gson.annotations.SerializedName

data class FlagsDto(

    @SerializedName("png")
    val png: String,

    @SerializedName("svg")
    val svg: String

)
