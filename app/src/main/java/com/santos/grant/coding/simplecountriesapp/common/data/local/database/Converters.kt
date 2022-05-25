package com.santos.grant.coding.simplecountriesapp.common.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    @TypeConverter
    fun fromLanguagesString(value: String) : List<String> {
        return Gson().fromJson<List<String>>(
            value,
            object : TypeToken<List<String>>(){}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toLanguagesString(list: List<String>) : String {
        return Gson().toJson(
            list,
            object : TypeToken<List<String>>(){}.type
        ) ?: "[]"
    }


}