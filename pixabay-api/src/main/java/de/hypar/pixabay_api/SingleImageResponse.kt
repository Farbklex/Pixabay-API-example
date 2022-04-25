package de.hypar.pixabay_api

/**
 * Item entry for a single image. Part of [PixaImageQueryResponse]
 */
data class SingleImageResponse(
    val id: Int,
    val pageUrl: String,
    val tags: List<String>,
    val previewURL: String,
    val webformatURL: String,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String,
)
