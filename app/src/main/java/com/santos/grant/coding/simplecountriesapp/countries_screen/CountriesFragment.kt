package com.santos.grant.coding.simplecountriesapp.countries_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.santos.grant.coding.simplecountriesapp.R
import com.santos.grant.coding.simplecountriesapp.common.BaseFragment
import com.santos.grant.coding.simplecountriesapp.common.data.local.entity.CountryEntity
import com.santos.grant.coding.simplecountriesapp.databinding.FragmentCountriesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountriesFragment : BaseFragment<FragmentCountriesBinding>(
    R.layout.fragment_countries
), CountriesListAdapter.Interaction {

    private val viewModel: CountriesViewModel by viewModels()

    private val adapter: CountriesListAdapter by lazy {
        CountriesListAdapter(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = getViewBinding(view)
        binding = _binding as FragmentCountriesBinding
        initViews()
        initObservers()
    }

    override fun getViewBinding(view: View): FragmentCountriesBinding {
        return FragmentCountriesBinding.bind(view)
    }

    override fun initViews() {
        binding.swipeRefresh.setOnRefreshListener {
            initData()
        }
        binding.recyclerView.adapter = adapter
    }

    override fun initData() {
        viewModel.getAllCountries()
    }

    override fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner) {
            handleState(it)
        }
    }

    private fun handleState(state: GetCountriesState) {
        binding.swipeRefresh.isRefreshing = state.isLoading
        adapter.submitList(state.countries)

        if (state.countries.isNullOrEmpty() && !state.isLoading) {
            binding.recyclerView.visibility = View.GONE
            binding.tvEmpty.visibility = View.VISIBLE
        } else {
            binding.recyclerView.visibility = View.VISIBLE
            binding.tvEmpty.visibility = View.GONE
        }

        if (state.error.isNotEmpty() && !state.isLoading) {
            binding.root.apply {
                Snackbar.make(
                    this,
                    state.error,
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    override fun onItemClick(country: CountryEntity) {
        val action =
            CountriesFragmentDirections.actionCountriesFragment2ToCountryDetailFragment(country)
        findNavController().navigate(action)
    }
}