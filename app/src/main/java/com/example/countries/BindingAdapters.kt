package com.example.countries

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.countries.listCountries.CountriesListAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<CountriesListQuery.Country>?) {
    val adapter = recyclerView.adapter as CountriesListAdapter
    adapter.submitList(data)
}

@BindingAdapter("imgUrl")
fun bindFlagImage(imgView: ImageView, code: String) {

    val imgUrl = "https://www.countryflags.io/${code.toLowerCase()}/flat/64.png"
    val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

    Glide.with(imgView.context)
        .load(imgUri)
        .apply(
            RequestOptions()
            .placeholder(R.drawable.loading_animation)
            .error(R.drawable.ic_broken_image))
        .into(imgView)

}