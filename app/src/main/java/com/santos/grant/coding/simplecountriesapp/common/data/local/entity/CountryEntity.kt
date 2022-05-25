package com.santos.grant.coding.simplecountriesapp.common.data.local.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val commonCountryName: String,
    val officialCountryName: String,
    val flagImageUrl: String,
    val languages: List<String>,
    val capital: String,
    val currency: String,
    val currencySymbol: String
) : Parcelable