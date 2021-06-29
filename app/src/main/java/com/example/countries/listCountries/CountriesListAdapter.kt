package com.example.countries.listCountries

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.CountriesListQuery
import com.example.countries.databinding.CountryListItemBinding

class CountriesListAdapter(private val clickListener: CountryClickListener) : ListAdapter<CountriesListQuery.Country, CountriesListAdapter.CountryViewHolder>(CountryDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = getItem(position)
        holder.bind(country, clickListener)
    }

    class CountryViewHolder private constructor( private var binding : CountryListItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind (country: CountriesListQuery.Country, clickListener: CountryClickListener){
            binding.country = country
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup) : CountryViewHolder{
                val inflater = LayoutInflater.from(parent.context)
                val binding = CountryListItemBinding.inflate(inflater)
                return CountryViewHolder(binding)
            }
        }
    }
}

class CountryDiffCallBack : DiffUtil.ItemCallback<CountriesListQuery.Country>() {

    override fun areItemsTheSame(oldItem: CountriesListQuery.Country, newItem: CountriesListQuery.Country): Boolean {
        return oldItem.code == newItem.code
    }

    @SuppressLint("DiffUtilsEqual")
    override fun areContentsTheSame(oldItem: CountriesListQuery.Country, newItem: CountriesListQuery.Country): Boolean {
        return oldItem == newItem
    }
}

class CountryClickListener(val clickListener: (code :String) -> Unit){
 fun onClick(code: String) = clickListener(code)
}