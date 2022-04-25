package de.hypar.pixabay_api

import de.hypar.pixabay_api.models.PixaImageQueryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * REST API for PixaBay
 */
interface PixaBayApi {
    @GET("/api/")
    suspend fun searchImages(
        @Query("key") key: String,
        @Query("q") searchTerm: String
    ): Response<PixaImageQueryResponse>
}