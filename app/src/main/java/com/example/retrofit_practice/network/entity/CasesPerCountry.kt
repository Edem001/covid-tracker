package com.example.retrofit_practice.network.entity

import java.text.NumberFormat

data class CasesPerCountry(
    val continent: String?,
    val country: String?,
    val iso: Int?,
    val capital_city: String?,
    val life_expectancy: Float?,
    val abbreviation: String?,
    val confirmed: Long?,
    val long: String?,
    val population: Long?,
    val sq_km_area: Double?,
    val elevation_in_meters: String?,
    val location: String?,
    val updated: String?,
    val deaths: Long?,
    val lat: String?
){
    fun formString(): String{
        val list = ArrayList<String>()

        list.add("Continent - $continent")
        list.add("Country - $country")
        list.add("Capital city - $capital_city")
        list.add("Life expectancy - $life_expectancy")
        list.add("Abbreviation - $abbreviation")
        list.add("Population - ${NumberFormat.getIntegerInstance().format(population)}")
        list.add("Location - $location")
        list.add("Confirmed - ${NumberFormat.getIntegerInstance().format(confirmed)}")
        list.add("Deaths - ${NumberFormat.getIntegerInstance().format(deaths)}")

        return list.filterNot { it.contains(Regex(" null$")) }.joinToString(separator = System.lineSeparator())
    }
}