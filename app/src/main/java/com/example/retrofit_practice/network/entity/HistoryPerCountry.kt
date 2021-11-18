package com.example.retrofit_practice.network.entity

import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

data class HistoryPerCountry(
    val continent: String?,
    val country: String?,
    val iso: Int?,
    val capital_city: String?,
    val life_expectancy: Float?,
    val elevation_in_meters: String?,
    val location: String?,
    val dates: Map<String, Int>,
    val abbreviation: String?,
    val population: Long?,
    val sq_km_area: Double?
) {
    override fun toString(): String {
        val list = ArrayList<String>()
        val format = SimpleDateFormat("yyyy-MM-dd")
        val localFormat = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())

        list.add("Continent - $continent")
        list.add("Country - $country")
        list.add("Capital city - $capital_city")
        list.add("Abbreviation - $abbreviation")
        list.add("Population - ${if (population == null) null else NumberFormat.getIntegerInstance().format(population)}")
        list.add("\n\n")
        list.add("Statistics by date:")

        dates.keys.forEach {
            val date = format.parse(it)
            list.add("${localFormat.format(date!!) ?: it}: ${NumberFormat.getIntegerInstance().format(dates[it])}")
        }

        return list.filterNot { it.contains(Regex(" null$")) }
            .joinToString(separator = System.lineSeparator())
    }
}