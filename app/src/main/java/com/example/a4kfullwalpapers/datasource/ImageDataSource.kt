package com.example.a4kfullwalpapers.datasource

import androidx.paging.PageKeyedDataSource
import com.example.a4kfullwalpapers.models.ImageData
import com.example.a4kfullwalpapers.models.Photo
import com.example.a4kfullwalpapers.retrofit.ApiClient
import com.example.a4kfullwalpapers.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ImageDataSource(var type: Int, var query: String) : PageKeyedDataSource<Int, Photo>() {
    var START_PAGE = 1
    var PAGE_COUNT = 50
    val apiService = ApiClient.getRetrofit().create(ApiService::class.java)
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Photo>
    ) {
        var call: Call<ImageData>? = null
        if (type == 1) {
            call = apiService.getImages(query = query, page = START_PAGE)
        } else if (type == 2) {
            call = apiService.getPopularImages(page = START_PAGE)
        } else if (type == 3) {
            call = apiService.getRandomImages(page = START_PAGE)
        }

        call?.enqueue(object : Callback<ImageData> {
            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if (response.isSuccessful) {
                    callback.onResult(response.body()?.photos!!, null, START_PAGE + 1)
                }
            }

            override fun onFailure(call: Call<ImageData>, t: Throwable) {

            }

        })

    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {}

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Photo>) {
        var call: Call<ImageData>? = null
        if (type == 1) {
            call = apiService.getImages(query = query, page = params.key)
        } else if (type == 2) {
            call = apiService.getPopularImages(page = params.key)
        } else if (type == 3) {
            call = apiService.getRandomImages(page = params.key )
        }
        call?.enqueue(object : Callback<ImageData> {
            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if (response.isSuccessful) {
                    val nextKey: Int? = if (params.key.toInt() == PAGE_COUNT) {
                        null
                    } else {
                        params.key + 1
                    }
                    callback.onResult(response.body()?.photos!!, nextKey)
                }
            }

            override fun onFailure(call: Call<ImageData>, t: Throwable) {

            }

        })
    }

}