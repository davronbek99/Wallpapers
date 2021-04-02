package com.example.a4kfullwalpapers.models

import java.io.Serializable

class Photo : Serializable {
    var height: Int? = null
    var id: Int? = null
    var liked: Boolean? = null
    var photographer: String? = null
    var photographer_id: Int? = null
    var photographer_url: String? = null
    var src: Src? = null
    var url: String? = null
    var width: Int? = null

    constructor(height: Int?, id: Int?, photographer: String?, src: Src?, width: Int?) {
        this.height = height
        this.id = id
        this.photographer = photographer
        this.src = src
        this.width = width
    }

    constructor()
}