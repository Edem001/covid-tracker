package com.example.retrofit_practice.network.entity.history

data class HistoryByCountry(
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

        list.add("Continent - $continent")
        list.add("Country - $country")
        list.add("Capital city - $capital_city")
        list.add("Abbreviation - $abbreviation")
        list.add("Population - $population")
        list.add("\n\n")
        list.add("Statistics by date:")

        dates.keys.forEach {
            list.add("$it: ${dates[it]}")
        }

        return list.filterNot { it.contains(Regex(" null$")) }
            .joinToString(separator = System.lineSeparator())
    }
}