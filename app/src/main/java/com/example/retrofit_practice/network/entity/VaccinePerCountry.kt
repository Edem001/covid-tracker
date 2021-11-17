package com.example.retrofit_practice.network.entity

import com.google.gson.annotations.SerializedName
import java.text.NumberFormat

data class VaccinePerCountry(
    @SerializedName("people_vaccinated")
    val peopleVaccinated: Long? = 0,
    @SerializedName("continent")
    val continent: String? = "",
    @SerializedName("country")
    val country: String? = "",
    @SerializedName("iso")
    val iso: Int? = 0,
    @SerializedName("people_partially_vaccinated")
    val peoplePartiallyVaccinated: Long? = 0,
    @SerializedName("capital_city")
    val capitalCity: String? = "",
    @SerializedName("life_expectancy")
    val lifeExpectancy: String? = "",
    @SerializedName("abbreviation")
    val abbreviation: String? = "",
    @SerializedName("population")
    val population: Int? = 0,
    @SerializedName("location")
    val location: String? = "",
    @SerializedName("administered")
    val administered: Int? = 0,
    @SerializedName("updated")
    val updated: String? = ""
) {

    override fun toString(): String {
        val dataList = ArrayList<String>()

        dataList.add("Abbreviation: $abbreviation")
        dataList.add("Continent: $continent")
        dataList.add("Population: ${NumberFormat.getIntegerInstance().format(population)}")

        dataList.add("People vaccinated: ${NumberFormat.getIntegerInstance().format(peopleVaccinated)}")
        dataList.add("People partially vaccinated: ${NumberFormat.getIntegerInstance().format(peoplePartiallyVaccinated)}")
        dataList.add("Administered: ${NumberFormat.getIntegerInstance().format(administered)}")
        dataList.add("Last update: $updated")

        return dataList.filterNot { it.contains(Regex(" null$")) }
            .joinToString(separator = System.lineSeparator())
    }
}




