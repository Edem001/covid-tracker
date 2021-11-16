package com.example.retrofit_practice

import com.example.retrofit_practice.network.entity.cases.CasesPerCountry
import com.example.retrofit_practice.network.entity.history.HistoryByCountry
import retrofit2.http.GET
import retrofit2.http.Query

interface CovidService{

    /**
     * Gets cases across the specific country.
     * Optional parameters: country, abbreviation (e.g. "UK"), continent(e.g. "Europe").
     * @param country optional
     * @param abbreviation optional
     */
    @GET("cases")
    suspend fun casesByCountry(
        @Query("country") country: String? = "Ukraine",
        @Query("ab") abbreviation: String? = null,
    ): Map<String, CasesPerCountry>

    /**
     * Gets cases across the world.
     * Optional parameters: continent(e.g. "Europe").
     * @param continent optional
     */
    @GET("cases")
    suspend fun casesByWorld(
        @Query("continent") continent: String? = null
    ): Map<String, Map<String, CasesPerCountry>>

    /**
     * Status is required, represents 2 states: Confirmed, Deaths
     * Optional parameters: country, abbreviation (e.g. "UK"), continent(e.g. "Europe").
     * Use one of optional parameters as global result is huge.
     * @param status required
     * @param country optional
     * @param abbreviation optional
     * @param continent optional.
     */
    @GET("history")
    suspend fun historyByCountry(
        @Query("status") status: String,
        @Query("country") country: String? = null,
        @Query("ab") abbreviation: String? = null,
        @Query("continent") continent: String? = null
    ): Map<String, HistoryByCountry>

    /**
     * Gets vaccination info across the world.
     * Optional parameters: country, abbreviation (e.g. "UK"), continent(e.g. "Europe").
     * @param country optional
     * @param abbreviation optional
     * @param continent optional.
     */
    @GET("vaccines")
    suspend fun vaccines(
        @Query("country") country: String? = null,
        @Query("ab") abbreviation: String? = null,
        @Query("continent") continent: String? = null
    )
}