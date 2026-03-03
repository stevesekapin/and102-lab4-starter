package com.codepath.campgrounds

import java.io.Serializable

data class Campground(
    val name: String,
    val description: String,
    val latLong: String,
    val images: List<String>
) : Serializable