package com.example.a4kfullwalpapers.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ImageEntity {

    @PrimaryKey
    var id: Int? = null

    var imgUrl: String? = null

    var author: String? = null

    var width: Int? = null

    var height: Int? = null

}