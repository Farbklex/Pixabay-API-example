package de.hypar.pixabay_api

/**
 * Response for PixaBay search image REST API call
 */
data class PixaImageQueryResponse(
    val hits: List<SingleImageResponse>
)
