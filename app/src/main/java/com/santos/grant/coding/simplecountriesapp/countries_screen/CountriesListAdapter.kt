package com.santos.grant.coding.simplecountriesapp.countries_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.santos.grant.coding.simplecountriesapp.common.data.local.entity.CountryEntity
import com.santos.grant.coding.simplecountriesapp.databinding.ItemCountryBinding

class CountriesListAdapter(
    private val interaction: Interaction
): ListAdapter<CountryEntity, CountriesListAdapter.ViewHolder>(ListComparator()) {

    inner class ViewHolder(val binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(country: CountryEntity) {
            binding.root.apply {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    setOnClickListener {
                        interaction.onItemClick(country)
                    }
                }
            }
            binding.apply {
                ivFlag.load(country.flagImageUrl)
                tvCountryName.text = country.commonCountryName
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemCountryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class ListComparator :  DiffUtil.ItemCallback<CountryEntity>() {

        override fun areItemsTheSame(oldItem: CountryEntity, newItem: CountryEntity) =
            oldItem.commonCountryName == newItem.commonCountryName

        override fun areContentsTheSame(oldItem: CountryEntity, newItem: CountryEntity) =
            oldItem == newItem
    }

    interface Interaction {

        fun onItemClick(country: CountryEntity)

    }
}