package com.santos.grant.coding.simplecountriesapp.country_detail_screen

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import coil.load
import com.santos.grant.coding.simplecountriesapp.R
import com.santos.grant.coding.simplecountriesapp.common.BaseFragment
import com.santos.grant.coding.simplecountriesapp.databinding.FragmentCountryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : BaseFragment<FragmentCountryDetailBinding>(
    R.layout.fragment_country_detail
) {

    private val args: CountryDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = getViewBinding(view)
        binding = _binding as FragmentCountryDetailBinding
        initViews()
        initObservers()
    }

    override fun initViews() {
        val country = args.country
        binding.apply {
            imageView.load(country.flagImageUrl)
            tvOfficialName.text = "Official Name: ${country.officialCountryName}"
            tvCommonName.text = "Common Name: ${country.commonCountryName}"
            tvLanguage.text = "Language/s: ${country.languages.joinToString(", ")}"
            tvCapital.text = "Capital: ${country.capital}"
            tvCurrency.text = "Currency: ${country.currency} (${country.currencySymbol})"
        }
    }

    override fun initData() {
    }

    override fun initObservers() {
    }

    override fun getViewBinding(view: View): FragmentCountryDetailBinding {
        return FragmentCountryDetailBinding.bind(view)
    }

}