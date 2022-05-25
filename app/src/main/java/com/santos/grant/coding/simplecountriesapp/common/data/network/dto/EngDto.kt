package com.santos.grant.coding.simplecountriesapp.common.data.network.dto

import com.google.gson.annotations.SerializedName

data class EngDto(
    @SerializedName("f")
    val f: String?,

    @SerializedName("m")
    val m: String?
)