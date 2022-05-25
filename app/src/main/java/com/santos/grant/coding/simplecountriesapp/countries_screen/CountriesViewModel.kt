package com.santos.grant.coding.simplecountriesapp.countries_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santos.grant.coding.simplecountriesapp.common.data.CountriesRepository
import com.santos.grant.coding.simplecountriesapp.common.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(
    private val repository: CountriesRepository
) : ViewModel() {

    private val _state = MutableLiveData(GetCountriesState())
    val state: LiveData<GetCountriesState> = _state


    fun getAllCountries() {
        repository.getAllCountries().onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.value = state.value?.copy(
                        isLoading = false,
                        error = result.message ?: "Unknown Error"
                    )
                }
                is Resource.Loading -> {
                    _state.value = state.value?.copy(
                        isLoading = true,
                        error = ""
                    )
                }
                is Resource.Success -> {
                    _state.value = state.value?.copy(
                        isLoading = false,
                        countries = result.data,
                        error = ""
                    )
                }
            }
        }.launchIn(viewModelScope)
    }
}