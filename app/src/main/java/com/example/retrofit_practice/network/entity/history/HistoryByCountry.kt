package com.example.retrofit_practice.network.entity.history

data class HistoryByCountry(val continent: String?,
                            val country: String?,
                            val iso: Int?,
                            val capital_city: String?,
                            val life_expectancy: Float?,
                            val elevation_in_meters: String?,
                            val location: String?,
                            val dates: Map<String, Int>,
                            val abbreviation: String?,
                            val population: Long?,
                            val sq_km_area: Double?)