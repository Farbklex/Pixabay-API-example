package de.hypar.pixabay_api.models

import com.squareup.moshi.JsonClass

/**
 * Response for PixaBay search image REST API call
 */
@JsonClass(generateAdapter = true)
data class PixaImageQueryResponse(
    val total: Int,
    val totalHits: Int,
    val hits: List<SingleImageResponse>
)
