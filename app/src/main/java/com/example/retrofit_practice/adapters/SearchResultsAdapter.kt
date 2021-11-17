package com.example.retrofit_practice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_practice.R
import com.example.retrofit_practice.network.entity.CasesPerCountry

class SearchResultsAdapter(context: Context, val data: Map<String, CasesPerCountry>) :
    RecyclerView.Adapter<SearchResultsAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val regionName = view.findViewById<TextView>(R.id.search_result_item_region_name)
        val regionInfo = view.findViewById<TextView>(R.id.search_result_item_region_info)
    }

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.search_results_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val key = data.keys.elementAt(position)
        holder.regionName.text = key
        holder.regionInfo.text = data[key]?.formString()
    }

    override fun getItemCount() =
        data.size
}