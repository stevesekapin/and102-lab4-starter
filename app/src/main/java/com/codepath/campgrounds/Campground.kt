package com.codepath.campgrounds

import java.io.Serializable

data class Campground(
    val name: String,
    val description: String,
    val latLong: String,
    val images: List<Image>
) : Serializable {

    fun getImageUrl(): String {
        return if (images.isNotEmpty()) {
            images[0].url
        } else {
            ""
        }
    }
}

data class Image(
    val url: String
) : Serializable