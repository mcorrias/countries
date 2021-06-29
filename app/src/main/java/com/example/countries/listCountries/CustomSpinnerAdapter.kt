package com.example.countries.listCountries

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.countries.R

class CustomSpinnerAdapter(
    context: Context,
    itemId: Int,
    continentList: Array<Continent>
) : ArrayAdapter<Continent>(context, itemId, continentList) {

    val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val view = if(convertView == null) {
            layoutInflater.inflate(R.layout.filter_continent_item, parent, false)

        }else{
            convertView
        }

        getItem(position)?.let { continent ->
            setItem(view, continent)
        }

        return view

    }

    override fun isEnabled(position: Int) =  position != 0

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = layoutInflater.inflate(R.layout.filter_continent_dropdown_item, parent, false)

        val tv = view.findViewById<TextView>(R.id.tvContinent)

        if(position == 0){
            tv.setTextColor(Color.GRAY)
        }else{
            tv.setTextColor(Color.BLACK)
        }

        getItem(position)?.let { continent ->
            setItem(view, continent)
        }

        return view
    }

    private fun setItem(view : View, continent :Continent){
        val tv = view.findViewById<TextView>(R.id.tvContinent)
        tv.text = continent.name
    }



}