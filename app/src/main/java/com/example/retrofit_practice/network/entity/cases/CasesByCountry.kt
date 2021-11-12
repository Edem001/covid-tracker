package com.example.retrofit_practice.network.entity.cases

data class CasesByCountry(
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
)