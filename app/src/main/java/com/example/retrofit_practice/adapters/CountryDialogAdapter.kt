package com.example.retrofit_practice.adapters

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_practice.R

class CountryDialogAdapter(
    private val allCountries: List<Int>,
    private val resources: Resources,
    private val context: Context,
    private val clickListener: CountryClickListener
) :
    RecyclerView.Adapter<CountryDialogAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val countryName = view.findViewById<TextView>(R.id.item_country_name)
    }


    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var countries = allCountries

    interface CountryClickListener{
        fun onCountryClick(selectedCountry: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_countries, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = countries[position]
        holder.countryName.text = resources.getString(country)
        holder.itemView.setOnClickListener {
            clickListener.onCountryClick(resources.getString(country))
        }
    }

    override fun getItemCount() = countries.size


    fun filter(query: String) {
        if (query.trim().isEmpty()) {
            countries = allCountries
        } else
            countries = allCountries.filter {
                resources.getString(it).contains(query.trim(), true)
            }.ifEmpty { listOf(R.string.no_matching_countries) }
        notifyDataSetChanged()
    }
}