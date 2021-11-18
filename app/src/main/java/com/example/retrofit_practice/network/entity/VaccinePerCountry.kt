package com.example.retrofit_practice.network.entity

import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
        val format = SimpleDateFormat("yyyy/MM/dd HH:mm:ss+SS")
        val localFormat = DateFormat.getDateTimeInstance(
            DateFormat.MEDIUM,
            DateFormat.MEDIUM,
            Locale.getDefault()
        )

        dataList.add("Abbreviation: $abbreviation")
        dataList.add("Continent: $continent")
        dataList.add(
            "Population: ${
                if (population == null) null else NumberFormat.getIntegerInstance()
                    .format(population)
            }"
        )

        dataList.add(
            "People vaccinated: ${
                if (peopleVaccinated == null) null else NumberFormat.getIntegerInstance()
                    .format(peopleVaccinated)
            }"
        )
        dataList.add(
            "People partially vaccinated: ${
                if (peoplePartiallyVaccinated == null) null else NumberFormat.getIntegerInstance()
                    .format(peoplePartiallyVaccinated)
            }"
        )
        dataList.add(
            "Administered: ${
                if (administered == null) null else NumberFormat.getIntegerInstance()
                    .format(administered)
            }"
        )
        dataList.add(
            "Last update: ${
                if (updated == null) null else
                    localFormat.format(format.parse(updated)!!)
            }"
        )

        return dataList.filterNot { it.contains(Regex(" null$")) }
            .joinToString(separator = System.lineSeparator())
    }
}




