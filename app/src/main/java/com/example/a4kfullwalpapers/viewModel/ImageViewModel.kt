package com.example.a4kfullwalpapers.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.a4kfullwalpapers.datasource.ImageDataSourceFactory
import com.example.a4kfullwalpapers.models.Photo
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ImageViewModel : ViewModel() {
    var imageDataSourceFactory: ImageDataSourceFactory? = null
    var executor: Executor? = null

    fun getImagePagedList(type: Int, query: String = ""): LiveData<PagedList<Photo>> {
        imageDataSourceFactory = ImageDataSourceFactory(type, query)

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(15)
            .setPageSize(50)
            .build()
        executor = Executors.newFixedThreadPool(1)
        var liveData = LivePagedListBuilder(imageDataSourceFactory!!, config)
            .setFetchExecutor((executor as ExecutorService?)!!)
            .build()

        return liveData
    }
}