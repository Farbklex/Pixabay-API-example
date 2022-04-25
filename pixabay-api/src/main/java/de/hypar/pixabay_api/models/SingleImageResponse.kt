package de.hypar.pixabay_api.models

import com.squareup.moshi.JsonClass

/**
 * Item entry for a single image. Part of [PixaImageQueryResponse]
 */
@JsonClass(generateAdapter = true)
data class SingleImageResponse(
    val id: Int,
    val pageUrl: String?,
    val type: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String?,
    val webformatWidth: Int,
    val webformatHeight: Int,
    val largeImageURL: String?,
    val fullHDURL: String?,
    val imageURL: String?,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user_id: Int,
    val user: String?,
    val userImageURL: String?
)
