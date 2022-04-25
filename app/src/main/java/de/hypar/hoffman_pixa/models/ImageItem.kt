package de.hypar.hoffman_pixa.models

/**
 * Model for an image with meta data
 */
data class ImageItem(
    val id: Int,
    val tags: List<String>,
    val thumbnailUrl: String,
    val imageUrl: String,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val user: String,
)
