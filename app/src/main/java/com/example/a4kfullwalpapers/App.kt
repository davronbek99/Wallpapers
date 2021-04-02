package com.example.a4kfullwalpapers

import android.app.Application
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import io.alterac.blurkit.BlurKit

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        BlurKit.init(this)

//        val builder = Picasso.Builder(this)
//        builder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
//        val built = builder.build()
//        built.setIndicatorsEnabled(true)
//        built.isLoggingEnabled = true
//        Picasso.setSingletonInstance(built)
    }
}