package com.example.a4kfullwalpapers.datasource

import androidx.paging.DataSource
import com.example.a4kfullwalpapers.models.Photo

class ImageDataSourceFactory(var type: Int, var query: String) : DataSource.Factory<Int, Photo>() {
    var imageDataSource: ImageDataSource? = null
    override fun create(): DataSource<Int, Photo> {
        imageDataSource = ImageDataSource(type, query)
        return imageDataSource!!
    }
}