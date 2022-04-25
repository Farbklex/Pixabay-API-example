package de.hypar.hoffman_pixa.models

import de.hypar.pixabay_api.models.SingleImageResponse

fun SingleImageResponse.toImageItem(): ImageItem {
    return ImageItem(
        id = this.id,
        tags = if (tags != null)
            this.tags!!.split(",")
        else listOf(""),
        thumbnailUrl = this.previewURL ?: "",
        imageUrl = this.webformatURL ?: "",
        downloads = this.downloads,
        likes = this.likes,
        comments = this.comments,
        user = this.user ?: "",
    )
}