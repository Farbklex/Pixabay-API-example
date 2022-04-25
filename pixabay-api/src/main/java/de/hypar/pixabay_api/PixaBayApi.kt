package de.hypar.pixabay_api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API for PixaBay
 */
interface PixaBayApi {
    @GET
    fun searchImages(
        @Query("key") key: String,
        @Query("q") searchTerm: String
    ): Call<List<PixaImageQueryResponse>>
}