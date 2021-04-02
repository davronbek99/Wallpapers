package com.example.a4kfullwalpapers.models

data class ImageData(
    val next_page: String,
    val page: Int,
    val per_page: Int,
    val photos: List<Photo>,
    val total_results: Int
)